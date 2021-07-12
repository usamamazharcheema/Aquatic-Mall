package com.example.androiddeveloper.aquatic_mall.Interfaces;

import com.example.androiddeveloper.aquatic_mall.FireBaseConnection.Topsellerslist;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.AdminGuestLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.AdminResponseLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.FeaturesResponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.GetchatresponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.InvestorLoginList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.InvestorLoginnewLIsts;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.LikesResponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.LoginResponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.MSG;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.MainResponselist;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.NewInvestorDetailLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.NewInvestyorLIsts;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.NewsResponseLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.NotificationResponseLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.NotificationdataList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.OpencommentsList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.PhoneresponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.PicResponse;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.StoriesResponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.TrendingLists;
import com.example.androiddeveloper.aquatic_mall.fragments.Feature_result;
import com.example.androiddeveloper.aquatic_mall.fragments.Featurecat_result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by ANDROID DEVELOPER on 22/01/2018.
 */

public interface ManageResponse {
    @FormUrlEncoded
    @POST("trending/selecttrending.php")
    Call<TrendingLists> GetTrending(@Field("test") String test);
    @FormUrlEncoded
    @POST("news/php/selectnewsnew.php")
    Call<NewsResponseLists> GetNews(@Field("test") int test,@Field("numbers") int numbers);

    @GET
    Call<MSG> getnewsrows(@Url String url);
    @FormUrlEncoded
    @POST("news/php/opencomments.php")
    Call<OpencommentsList> GetOpneComments(@Field("id") String test);

    @FormUrlEncoded
    @POST("news/php/insertlike.php")
    Call<MSG> InsertLikes(@Field("newsid") String newsid,@Field("email") String email);

    @FormUrlEncoded
    @POST("news/php/getlikesdata.php")
    Call<LikesResponseList> GetLikes(@Field("id") String newsid);


    @FormUrlEncoded
    @POST("topseller/requesttobook.php")
    Call<MSG> requestobook(@Field("name") String name,@Field("email") String email,@Field("phone") String phone,@Field("cnic") String cnic,@Field("unit") String unit,@Field("shopcode") String shopcode,@Field("floor") String floor,@Field("item") String item);

    @FormUrlEncoded
    @POST("news/php/insertcomment.php")
    Call<MSG> InsertComment(@Field("newsid") String newsid,@Field("email") String email,@Field("name") String name,@Field("commentdetail") String commentdetail,@Field("img") String img,@Field("newsdetail") String newsdetail);
    @FormUrlEncoded
    @POST("features/php/selectfeatures.php")
    Call<FeaturesResponseList> GetFeatureName(@Field("test") String test);
    @FormUrlEncoded
    @POST("features/php/picturesload.php")
    Call<PicResponse> GetPic(@Field("pic") String pic);
    @FormUrlEncoded
    @POST("ratestructure/rate.php")
    Call<MainResponselist> numberrworesponse(@Field("rows") int rows);
    @GET
    Call<Feature_result> getTopRatedMovies(@Url String url);
    @GET
    Call<Featurecat_result> getTopRated(@Url String url);

    @FormUrlEncoded
    @POST("login/investoraqlogin.php")
    Call<InvestorLoginList> investorlogin(@Field("rows") int rows);


    @FormUrlEncoded
    @POST("login/investorptopertynewupdate.php")
    Call<NewInvestyorLIsts> investornewproprty(@Field("code") String code);

    @FormUrlEncoded
    @POST("login/investmenttablenewedition1.php")
    Call<NewInvestorDetailLists> investordetailnew(@Field("code") String code,@Field("shopecode") String shopcode);


    @FormUrlEncoded
    @POST("login/investordetailnewupdate.php")
    Call<NewInvestorDetailLists> investordetailnewrental(@Field("code") String code,@Field("shopecode") String shopcode);
    @FormUrlEncoded
    @POST("login/investorsignupnew.php")
    Call<InvestorLoginnewLIsts> investorloginnew(@Field("code") String code, @Field("cnic") String cnic);

    @FormUrlEncoded
    @POST("login/investoraqlogin.php")
    Call<com.example.androiddeveloper.aquatic_mall.fragments.MainResponselist> numberrworesponses(@Field("rows") int rows);
    @FormUrlEncoded
    @POST("login/investor_installments.php")
    Call<com.example.androiddeveloper.aquatic_mall.fragments.MainResponselist> ivestor_ints(@Field("rows") int rows);

    @FormUrlEncoded
    @POST("news/php/stories.php")
    Call<StoriesResponseList> stories(@Field("rows") int rows);
    @FormUrlEncoded
    @POST("login/investor_profile.php")
    Call<com.example.androiddeveloper.aquatic_mall.fragments.MainResponselist> ivestor_profile(@Field("rows") int rows);


    @FormUrlEncoded
    @POST("login/loginemail.php")
    Call<MSG> emaillogin(@Field("code") String code);

    @FormUrlEncoded
    @POST("login/investorlogin.php")
    Call<PhoneresponseList> codeverify(@Field("code") String code);
    @POST("login/signup.php")
    Call<MSG> GmailData(@Field("name") String gmail_name, @Field("email") String gmail_email );
    @FormUrlEncoded
    @POST("login/signup.php")
    Call<MSG> insertData(@Field("name") String name, @Field("email") String email , @Field("phonenumber") String phonenumber, @Field("password") String password);
    @FormUrlEncoded
    @POST("login/login.php")
    Call<LoginResponseList> insertlogin(@Field("email") String email , @Field("password") String password);


    @FormUrlEncoded
    @POST("login/logout.php")
    Call<MSG> logout(@Field("tokens") String tokens);


    @FormUrlEncoded
    @POST("selling/selling.php")
    Call<MSG> Selling_Data(@Field("investorcode") String gmail_name, @Field("unitcode") String gmail_email );
    @GET
    Call<MSG> sendnotify(@Url String url);
    @GET
    Call<Topsellerslist> gettopsellers(@Url String url);
    @GET
    Call<MSG> getnotification(@Url String url);
    @GET
    Call<NotificationResponseLists> notificationresponse(@Url String url);
    @FormUrlEncoded
    @POST("topseller/register.php")
    Call<MSG> inserttoken(@Field("token") String code );
    @FormUrlEncoded
    @POST("topseller/sendnotify.php")
    Call<MSG> sendnotification(@Field("token") String code);
    @FormUrlEncoded
    @POST("topseller/dataofinvestor.php")
    Call<NotificationdataList> getnotificationdata(@Field("id") String code);
    @GET
    Call<AdminResponseLists> getadminslist(@Url String url);
    @FormUrlEncoded
    @POST("chat/insertadminstoken.php")
    Call<MSG> insertadmin(@Field("token") String code );
    @FormUrlEncoded
    @POST("chat/admin_guest_chat.php")
    Call<MSG> sendmessageguest(@Field("email") String email,@Field("name") String name,@Field("message") String message,@Field("token") String code,@Field("sender") String sender );

    @FormUrlEncoded
    @POST("chat/getchat.php")
    Call<GetchatresponseList> getchat(@Field("email") String email );
    @GET
    Call<AdminGuestLists> adminguestchatlists(@Url String url);
    @FormUrlEncoded
    @POST("chat/adminchatlist.php")
    Call<MSG> sendmessageadmin(@Field("email") String email,@Field("name") String name,@Field("message") String message,@Field("token") String code,@Field("sender") String sender );


}
