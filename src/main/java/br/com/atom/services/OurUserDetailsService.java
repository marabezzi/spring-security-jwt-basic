package br.com.atom.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.atom.repositories.OurUserRepository;

@Service
public class OurUserDetailsService implements UserDetailsService {
	
	private OurUserRepository repository;

	public OurUserDetailsService(OurUserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByEmail(username).orElseThrow();
	}

}
