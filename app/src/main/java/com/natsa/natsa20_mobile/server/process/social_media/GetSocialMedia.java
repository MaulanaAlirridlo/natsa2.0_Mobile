package com.natsa.natsa20_mobile.server.process.social_media;

import android.widget.ArrayAdapter;

import com.natsa.natsa20_mobile.model.social_media.GetSocialMediaResponse;
import com.natsa.natsa20_mobile.model.social_media.SocialMedia;
import com.natsa.natsa20_mobile.model.vestiges.Data;
import com.natsa.natsa20_mobile.server.RetrofitBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetSocialMedia {

    public static List<Integer> socialMediaIdList = new ArrayList<>();
    public static List<String> socialMediaStringList = new ArrayList<>();

    public static List<Integer> getSocialMediaIdList() {
        return socialMediaIdList;
    }

    public static List<String> getSocialMediaStringList() {
        return socialMediaStringList;
    }

    void setSocialMedia(List<SocialMedia> vestigesDataList, ArrayAdapter<String> adapter){
        socialMediaStringList.clear();
        socialMediaIdList.clear();
        socialMediaStringList.add("---");
        socialMediaIdList.add(null);
        for (Iterator<SocialMedia> i = vestigesDataList.iterator(); i.hasNext();) {
            SocialMedia socialMedia = i.next();
            socialMediaIdList.add(socialMedia.getId());
            socialMediaStringList.add(socialMedia.getSosmed());
        }
        adapter.notifyDataSetChanged();
    }

    public void getSocialMediaFromApi(ArrayAdapter<String> adapter){
        RetrofitBuilder.endPoint().getSocialMedia().enqueue(new Callback<GetSocialMediaResponse>() {
            @Override
            public void onResponse(Call<GetSocialMediaResponse> call, Response<GetSocialMediaResponse> response) {
                assert response.body() != null;
                List<SocialMedia> data = response.body().getData();
                setSocialMedia(data, adapter);
            }

            @Override
            public void onFailure(Call<GetSocialMediaResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
