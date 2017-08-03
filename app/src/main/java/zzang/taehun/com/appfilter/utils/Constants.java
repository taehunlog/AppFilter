package zzang.taehun.com.appfilter.utils.xml;

/**
 * Created by taehun on 2017-06-15.
 */

public class Constants {
    /*메인 고정값들*/

     // FIXME: 2017-06-19  번호 여기에 고정 사용? 웹에서 받아서 사용?
    public static final String CALL_NUMBER="01092227953";
    public static final String CALL_NUMBER1="openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList?tmX=244148.546388&tmY=412423.75772&pageNo=1&numOfRows=10&ServiceKey=yjxbBD8bhhkgxWURpzKvfg4qS88k%2BFznDHef0CskHjcNSogjFB3EWKk6UX0No9aln7J3FsjIa%2B8fElMoc1Xc9Q%3D%3D";

    public static final String API_BASE="http://openapi.airkorea.or.kr/";
    public static final String API_BASE_Air=API_BASE+"openapi/services/rest/MsrstnInfoInqireSvc/";
    public static final String API_BASE_Air_NearList=API_BASE_Air+"getNearbyMsrstnList";
    public static final String API_SERVICE_KEY="yjxbBD8bhhkgxWURpzKvfg4qS88k%2BFznDHef0CskHjcNSogjFB3EWKk6UX0No9aln7J3FsjIa%2B8fElMoc1Xc9Q%3D%3D";


//    public static final String API_BASE="http://www.platformhappy.co.kr:20001";
    public static final String API_FOLDER="/api";
    public static final String API_SET_PUSH_KEY=API_BASE+API_FOLDER+"/putApi.php";


    /*푸시 관련*/
    public static final String FCM_PUSH_KEY="FCM_PUSH_KEY";
    public static final int NOTIFY_ID=1593;
    public static final String NOTIFY_EVENT="event";
    public static final String NOTIFY_WELCOM_MSG="welcom_msg";

    /*프리퍼런스 관련*/
    public static final String SHARED_PREFERENCES_NAME="ProServiceFile";
    public static final String SP_PUSH_KEY_SENT="SP_PUSH_KEY_SENT";
}
