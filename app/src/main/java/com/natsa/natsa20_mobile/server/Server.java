package com.natsa.natsa20_mobile.server;

public class Server {
    //wsjti
    public static final String url = "https://wsjti.id/natsa/public/";
    public static final String urlWithoutSlash = "https://wsjti.id/natsa/public";
    public static final String urlApi = "https://wsjti.id/natsa/public/api/";

//    local
//    public static final String url = "http://192.168.1.2:8000/";
//    public static final String urlWithoutSlash = "http://192.168.1.2:8000";
//    public static final String urlApi = "http://192.168.1.2:8000/api/";


    //storage
    public static final String storage = url+"storage/";

    //lupa password
    public static final String forgetPassword = url+"forgot-password/";

    //sawah
    public static final String riceFields = "riceFields";
    public static final String product = "product";

    public static final String riceFieldsWithSlash = "riceFields/";
    public static final String productWithSlash = "product/";
    public static final String searchProductWithSlash = riceFieldsWithSlash+"search/";
    public static final String deletePhotoWithSlash = riceFieldsWithSlash+"delete/photo/";
    public static final String ketersediaanWithSlash = riceFieldsWithSlash+"ketersediaan/";

    public static final String searchProduct = riceFieldsWithSlash+"search";
    public static final String deletePhoto = riceFieldsWithSlash+"delete/photo";
    public static final String ketersediaan = riceFieldsWithSlash+"ketersediaan";
    //user
    public static final String user = "user";
    public static final String users = "users";

    public static final String userWithSlash = "user/";
    public static final String usersWithSlash = "users/";
    public static final String registerWithSlash = usersWithSlash+"register/";
    public static final String loginWithSlash = usersWithSlash+"login/";
    public static final String logoutWithSlash = usersWithSlash+"logout/";
    public static final String userDetailsWithSlash = userWithSlash+"details/";
    public static final String updatePasswordWithSlash = usersWithSlash+"password/";

    public static final String register = usersWithSlash+"register";
    public static final String login = usersWithSlash+"login";
    public static final String logout = usersWithSlash+"logout";
    public static final String userDetails = userWithSlash+"details";
    public static final String updatePassword = usersWithSlash+"password";


    //makelar
    public static final String makelar = "makelar";
    public static final String makelarWithSlash = "makelar/";

    //history
    public static final String history = "history";
    public static final String historyWithSlash = "history/";

    //bookmark
    public static final String bookmarks = "bookmarks";
    public static final String bookmarksWithSlash = "bookmarks/";

    //regions
    public static final String regions = "regions";
    public static final String regionsWithSlash = "regions/";

    //vestiges
    public static final String vestiges = "vestiges";
    public static final String vestigesWithSlash = "vestiges/";

    //irrigations
    public static final String irrigations = "irrigations";
    public static final String irrigationsWithSlash = "irrigations/";

    //social media
    public static final String socialMedia = "social-media";
    public static final String userSocialMedia = "user/"+socialMedia;

    public static final String socialMediaWithSlash = "social-media/";
    public static final String userSocialMediaWithSlash = "user/"+socialMedia;
}
