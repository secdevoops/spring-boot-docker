package es.secdevoops.springboot.docker.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.secdevoops.springboot.docker.dto.UserAccountDTO;
import es.secdevoops.springboot.docker.mappers.UserAccountMapper;
import es.secdevoops.springboot.docker.model.Role;
import es.secdevoops.springboot.docker.model.UserAccount;
import es.secdevoops.springboot.docker.repository.RoleRepository;
import es.secdevoops.springboot.docker.repository.UserAccountRepository;

@RestController
@RequestMapping("/users")
public class UserAccountController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostMapping("/register")
	public void register(@RequestBody UserAccountDTO userAccountDTO) {
		UserAccount userAccount = UserAccountMapper.toUserAccount(userAccountDTO);
		userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
		userAccount.setRoles(new ArrayList<Role>());
		userAccount.getRoles().add(roleRepository.findByRolename(Role.USER_ROLE));
		try {
			userAccountRepository.save(userAccount);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@PostMapping("/registeradmin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void registerAdmin(@RequestBody UserAccountDTO userAccountDTO) {
		UserAccount userAccount = UserAccountMapper.toUserAccount(userAccountDTO);
		userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
		userAccount.setRoles(new ArrayList<Role>());
		userAccount.getRoles().add(roleRepository.findByRolename(Role.ADMIN_ROLE));
		try {
			userAccountRepository.save(userAccount);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
