package tklimczak.atm.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tklimczak.atm.model.Account;

public class AccountPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;

    private String number;

    @JsonIgnore
    private String pin;

    private Collection<? extends GrantedAuthority> authorities;

    public AccountPrincipal(String number, String pin, Collection<? extends GrantedAuthority> authorities) {
        this.number = number;
        this.pin = pin;
        this.authorities = authorities;
    }

    public static AccountPrincipal create(Account account) {
        List<GrantedAuthority> authorities = account.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new AccountPrincipal(
        		account.getNumber(),
        		account.getPin(),
                authorities
        );
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String getUsername() {
        return number;
    }

    @Override
    public String getPassword() {
        return pin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountPrincipal that = (AccountPrincipal) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number);
    }
}
