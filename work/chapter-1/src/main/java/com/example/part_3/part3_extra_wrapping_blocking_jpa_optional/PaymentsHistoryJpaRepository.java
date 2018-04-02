package com.example.part_3.part3_extra_wrapping_blocking_jpa_optional;

import java.util.List;

public interface PaymentsHistoryJpaRepository {
	List<Payment> findAllByUserId(String userId);
}
