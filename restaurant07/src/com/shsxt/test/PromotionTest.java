package com.shsxt.test;


import org.junit.Test;

import com.shsxt.model.Promotion;
import com.shsxt.service.PromotionService;

public class PromotionTest {

	@Test
	public void test() {
		PromotionService promotionService = new PromotionService();
//		Page<Promotion> page = promotionService.findPromotionByPage(1, "1", "1");
//		System.out.println(page.getDatas().get(0).getPromotionName());
		Promotion promotion = promotionService.queryPromotionById("1");
		System.out.println(promotion.getContent());
	}

}
