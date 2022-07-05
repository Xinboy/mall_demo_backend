package com.xinbo.mall_demo.model.bo;

import com.xinbo.mall_demo.mbg.model.UmsAdmin;
import com.xinbo.mall_demo.mbg.model.UmsResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 * @author Xinbo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserDetails implements UserDetails {
    // 后台用户
    private UmsAdmin umsAdmin;

    //拥有资源列表
    private List<UmsResource> resourceList;

    public AdminUserDetails(UmsAdmin umsAdmin) {
        this.umsAdmin = umsAdmin;
    }

    // 返回的值不能为null,否则返回的永远是null,就会一直没有权限，由此定义了一个authorities 属性并提供get方法，因为自
    // 定义了UserDetails，就没有在UserService中，使用到框架提供的User对象
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 模拟一个admin的权限
        System.out.println(AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        //返回当前用户的角色
//        return resourceList.stream()
//                .map(role->new SimpleGrantedAuthority(role.getId()+":"+role.getName()))
//                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
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
        return umsAdmin.getStatus().equals(1);
    }
}
