package org.wecancodeit.pantryplus;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface LineItemRepository extends CrudRepository<LineItem, Long> {
	// Collection<LineItem> findLineItems;
}
