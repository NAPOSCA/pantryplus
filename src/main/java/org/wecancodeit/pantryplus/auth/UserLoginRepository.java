package org.wecancodeit.pantryplus.auth;

import org.springframework.data.repository.CrudRepository;

public interface UserLoginRepository extends CrudRepository<UserLogin, Long> {
	UserLogin findByUsername (String username);
}
