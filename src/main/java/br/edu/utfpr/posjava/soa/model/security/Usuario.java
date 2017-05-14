package br.edu.utfpr.posjava.soa.model.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.*;

@Entity
public class Usuario implements UserDetails {
	private static final long serialVersionUID = 1L;
	private static final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@Column(length = 50, nullable = false)
	@Setter
	private String username;
	
	@Column(length = 512, nullable = false)
	@Setter
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Setter
	private Set<Permissao> permissoes;
	
	private Date lastPasswordReset;
	
	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auto = new ArrayList<>();
		auto.addAll(getPermissoes());
		return auto;
	}
	
	public void addPermissao(Permissao permissao){
		if (permissoes == null){
			permissoes = new HashSet<>();
		}
		permissoes.add(permissao);
	}
	
	public String getEncodedPassword(String pass){
		if (! pass.isEmpty()){
			return bCrypt.encode(pass);
		}
		return pass;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@JsonIgnore
	public Date getLastPasswordReset() {
		return this.lastPasswordReset;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}
}
