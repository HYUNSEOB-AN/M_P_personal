# 과일 농장
# 실행 환경
> + SDK버전 : Android 11(R)
> + Activity : LoginActivity, MainActivity, RegisterActivity, ProfileActivity
>> 1. LoginActivity(로그인 화면)
>> + email 형식에 맞지 않을시 메시지 호출, password 영/한 최소 6글자(특수문자 가능)가 아닐시 메시지 호출
>> + 초기화시에 최근 로그인 정보를 저장 가능(Reference를 통한 정보 저장)
>> + 회원가입 클릭시 RegisterActivity로 화면전환
>> + 회원가입한 정보가 Firebase Database에 저장 되어있으면 로그인 버튼을 눌렀을 때 로그인 성공!
>> 
>> 2. RegisterActivity
>> + email 형식의 회원가입
>> + password는 영/한 최소 6글자(특수문자 가능)
>> + Phone Number는 010-####-#### 형식
>> + 이용약관에 동의 하지않을시 회원가입 불가능, accpet에 체크가 안되어있을 시 메시지 호출
>> + 위의 모든 형식이 맞다면 Sign Up 버튼을 눌렀을 때 Firebase Database에 회원정보 저장과 함께 회원가입 성공!

>> 3. MainActivity
>> + 체크박스가 클릭되어 있을 경우 상품을 등록/삭제 할 수 있음
>> + 상품 등록시 과일은 랜덤으로 등록
>> + 로그아웃 버튼 클릭시 로그아웃과 함께 로그인 화면으로 전환
>> + 회원정보 버튼 클릭시 ProfileActivity로 화면 전환

>> 4. ProfileActivity
>> + 현재 로그인 중인 유저의 회원정보(Firebase Database 의 저장된 정보)를 확인 가능
>> + 확인 버튼 클릭시 MainActivity로 화면전환
>> 
> + Class : Users, info, PreferenceManager
>> 1. Users.class : Firebase에 회원정보를 저장 하기 위한 class
>> 2. PreferenceManager.class : 로그인화면에서 회원정보를 저장, 호출을 위한 get,set 메소드를 가진 클래스
>> 3. info.class : Firebase에서 회원정보를 호출하기 위한 class
>> 
> + Layout : activity_login, activity_main, activity_profile, activity_register ==========> xml 파일들
>>  
> + Drawable : apple.png, banana.jpg, fruit.jpg, peach.jpg, pear.jpg, persimmon.jpg ==========> 과일사진들

