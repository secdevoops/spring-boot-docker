package es.secdevoops.springboot.docker.mappers;

import es.secdevoops.springboot.docker.dto.UserAccountDTO;
import es.secdevoops.springboot.docker.model.UserAccount;

public class UserAccountMapper {
	
	public static UserAccount toUserAccount(UserAccountDTO userAccountDTO) {
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(userAccountDTO.getUsername());
		userAccount.setPassword(userAccountDTO.getPassword());
		return userAccount;
	}
	
	public static UserAccountDTO toUserAccountDTO(UserAccount userAccount) {
		UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setUsername(userAccount.getUsername());
		userAccountDTO.setPassword(userAccount.getPassword());
		return userAccountDTO;
	}
}
