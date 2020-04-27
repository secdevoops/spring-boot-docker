package es.secdevoops.springboot.docker.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.secdevoops.springboot.docker.model.Role;
import es.secdevoops.springboot.docker.model.UserAccount;
import es.secdevoops.springboot.docker.repository.UserAccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired UserAccountRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount user = userRepository.findByUsername(username);
		if( user == null) {
			return null;
		}
		
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for(Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
		}
		
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), 
				user.isCredentialsNonExpired(), user.isAccountNonLocked(), grantedAuthorities);
	}

}
