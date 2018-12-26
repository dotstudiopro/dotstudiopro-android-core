package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by user on 15-06-2016.
 */
public class RecommendedItemPairDTO {
    private RecommendedItemDTO recommendedItemDTO1;
    private RecommendedItemDTO recommendedItemDTO2;

    public RecommendedItemDTO getRecommendedItemDTO1() {
        return recommendedItemDTO1;
    }

    public void setRecommendedItemDTO1(RecommendedItemDTO recommendedItemDTO1) {
        this.recommendedItemDTO1 = recommendedItemDTO1;
    }

    public RecommendedItemDTO getRecommendedItemDTO2() {
        return recommendedItemDTO2;
    }

    public void setRecommendedItemDTO2(RecommendedItemDTO recommendedItemDTO2) {
        this.recommendedItemDTO2 = recommendedItemDTO2;
    }
}
