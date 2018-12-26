package com.dotstudioz.dotstudioPRO.corelibrary.util;

import com.dotstudioz.dotstudioPRO.models.dto.CategoriesDTO;
import com.dotstudioz.dotstudioPRO.models.dto.VideoInfoDTO;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 24-06-2016.
 */
public class CollectionsSwapUtility {

    public static void sortDataIfNotSorted(CategoriesDTO seriesVideoCategoriesDTO, List<VideoInfoDTO> videoInfoDTOArrayList) {
        try {
            for (int i = 0; i < seriesVideoCategoriesDTO.getVideosList().size(); i++) {
                if (!videoInfoDTOArrayList.get(i).getVideoID().equals(seriesVideoCategoriesDTO.getVideosList().get(i))) {
                    for (int j = 0; j < videoInfoDTOArrayList.size(); j++) {
                        if (videoInfoDTOArrayList.get(j).getVideoID().equals(seriesVideoCategoriesDTO.getVideosList().get(i))) {
                            try {
                                Collections.swap(videoInfoDTOArrayList, i, j);
                                break;
                            } catch (Exception e) {
                                //e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
