package org.wecancodeit.pantryplus.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<PantryUser, Long> {

}
