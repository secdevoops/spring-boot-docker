package es.secdevoops.springboot.docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.secdevoops.springboot.docker.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	public Role findByRolename(String rolename);
}
