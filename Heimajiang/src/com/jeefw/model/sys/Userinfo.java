Ęþšū   3|  com/jeefw/model/sys/Userinfo  +com/jeefw/model/sys/param/UserinfoParameter serialVersionUID J ConstantValueic userId Ljava/lang/Integer; RuntimeVisibleAnnotations Ljavax/persistence/Id; "Ljavax/persistence/GeneratedValue; Ljavax/persistence/Column; name UserID gameID GameID 	protectID 	ProtectID 
passwordID 
PasswordID 
spreaderID 
SpreaderID accounts Ljava/lang/String; Accounts length    nullable     nickName NickName regAccounts RegAccounts 
underWrite 
UnderWrite   ú 
passPortID 
PassPortID    compellation Compellation    	logonPass 	LogonPass     
insurePass 
InsurePass faceID FaceID customID CustomID present Present 	userMedal 	UserMedal 
experience 
Experience 
loveLiness 
LoveLiness 	userRight 	UserRight masterRight MasterRight serviceRight ServiceRight masterOrder MasterOrder memberOrder MemberOrder memberOverDate Ljava/util/Date; MemberOverDate Ljavax/persistence/Temporal; value  Ljavax/persistence/TemporalType; DATE memberSwitchDate MemberSwitchDate customFaceVer CustomFaceVer gender Gender nullity Nullity nullityOverDate NullityOverDate stunDown StunDown moorMachine MoorMachine 	isAndroid 	IsAndroid webLogonTimes WebLogonTimes gameLogonTimes GameLogonTimes playTimeCount PlayTimeCount onLineTimeCount OnLineTimeCount lastLogonIP LastLogonIP    lastLogonDate LastLogonDate lastLogonMobile LastLogonMobile    lastLogonMachine LastLogonMachine 
registerIP 
RegisterIP registerDate RegisterDate registerMobile RegisterMobile registerMachine RegisterMachine id Ljavax/persistence/Transient; score Ljava/math/BigInteger; winCount 	lostCount 	drawCount 	fleeCount insureScore <init> ()V Code
     LineNumberTable LocalVariableTable this Lcom/jeefw/model/sys/Userinfo; 	getUserId ()Ljava/lang/Integer;	   
  	setUserId (Ljava/lang/Integer;)V 	getGameID	     	setGameID getProtectID	     setProtectID getPasswordID	     setPasswordID getSpreaderID	      setSpreaderID getAccounts ()Ljava/lang/String;	  Ĩ   setAccounts (Ljava/lang/String;)V getNickName	  Š !  setNickName getRegAccounts	  Ū #  setRegAccounts getUnderWrite	  ē %  setUnderWrite getPassPortID	  ķ (  setPassPortID getCompellation	  š +  setCompellation getLogonPass	  ū .  setLogonPass getInsurePass	  Â 1  setInsurePass 	getFaceID	  Æ 3  	setFaceID getCustomID	  Ę 5  setCustomID 
getPresent	  Î 7  
setPresent getUserMedal	  Ō 9  setUserMedal getExperience	  Ö ;  setExperience getLoveLiness	  Ú =  setLoveLiness getUserRight	  Þ ?  setUserRight getMasterRight	  â A  setMasterRight getServiceRight	  æ C  setServiceRight getMasterOrder	  ę E  setMasterOrder getMemberOrder	  î G  setMemberOrder getMemberOverDate ()Ljava/util/Date;	  ó I J setMemberOverDate (Ljava/util/Date;)V getMemberSwitchDate	  ø P J setMemberSwitchDate getCustomFaceVer	  ü R  setCustomFaceVer 	getGender	   T  	setGender 
getNullity	  V  
setNullity getNullityOverDate	  X J setNullityOverDate getStunDown	  Z  setStunDown getMoorMachine	  \  setMoorMachine getIsAndroid	  ^  setIsAndroid getWebLogonTimes	  `  setWebLogonTimes getGameLogonTimes	  b  setGameLogonTimes getPlayTimeCount	   d  setPlayTimeCount getOnLineTimeCount	 $ f  setOnLineTimeCount getLastLogonIP	 ( h  setLastLogonIP getLastLogonDate	 , k J setLastLogonDate getLastLogonMobile	 0 m  setLastLogonMobile getLastLogonMachine	 4 p  setLastLogonMachine getRegisterIP	 8 r  setRegisterIP getRegisterDate	 < t J setRegisterDate getRegisterMobile	 @ v  setRegisterMobile getRegisterMachine	 D x  setRegisterMachine getScore ()Ljava/math/BigInteger;	 I | } setScore (Ljava/math/BigInteger;)V getWinCount	 N ~  setWinCount getLostCount	 R   setLostCount getDrawCount	 V   setDrawCount getFleeCount	 Z   setFleeCount getInsureScore	 ^  } setInsureScore getId	 b z  setId 
SourceFile Userinfo.java Ljavax/persistence/Entity; Ljavax/persistence/Table; AccountsInfo !Lorg/hibernate/annotations/Cache; region all usage 4Lorg/hibernate/annotations/CacheConcurrencyStrategy; 
READ_WRITE 4Lorg/codehaus/jackson/annotate/JsonIgnoreProperties; 
maxResults firstResult topCount sortColumns cmd queryDynamicConditions sortedConditions dynamicProperties success message sortColumnsString flag !     5           
                s             s             s             s             s             s  I  Z    !          s " I  Z    #          s $ I  Z    %          s & I ' Z    (          s ) I * Z    +          s , I - Z    .          s / I 0 Z    1          s 2 I 0 Z    3          s 4  5          s 6  7          s 8  9          s :  ;          s <  =          s >  ?          s @  A          s B  C          s D  E          s F  G          s H  I J         s K L  Me N O  P J         s Q L  Me N O  R          s S  T          s U  V          s W  X J         s Y L  Me N O  Z          s [  \          s ]  ^          s _  `          s a  b          s c  d          s e  f          s g  h          s i I j Z    k J         s l L  Me N O  m          s n I o Z    p          s q I 0 Z    r          s s I j Z    t J         s u L  Me N O  v          s w I o Z    x          s y I 0 Z    z        {    | }       {    ~        {            {            {            {     }       {   i        3     *· ą       
                          /     *ī °                               >     *+ĩ ą       
                      
          /     *ī °           Ģ                    >     *+ĩ ą       
    Ļ  Đ                          /     *ī °           ­                    >     *+ĩ ą       
    ē  ģ                          /     *ī °           ·                    >     *+ĩ ą       
    ž  ―                          /     *ī °           Á              Ą      >     *+ĩ ą       
    Æ  Į                    Ē Ģ     /     *ī Ī°           Ë              Ķ §     >     *+ĩ Īą       
    Ð  Ņ                    Ļ Ģ     /     *ī Đ°           Õ              Ŧ §     >     *+ĩ Đą       
    Ú  Û                !    Ž Ģ     /     *ī ­°           ß              Ŋ §     >     *+ĩ ­ą       
    ä  å                #    ° Ģ     /     *ī ą°           é              ģ §     >     *+ĩ ąą       
    î  ï                %    ī Ģ     /     *ī ĩ°           ó              · §     >     *+ĩ ĩą       
    ø  ų                (    ļ Ģ     /     *ī đ°           ý              ŧ §     >     *+ĩ đą       
                    +    ž Ģ     /     *ī ―°                        ŋ §     >     *+ĩ ―ą       
                    .    Ā Ģ     /     *ī Á°                        Ã §     >     *+ĩ Áą       
                    1    Ä      /     *ī Å°                        Į      >     *+ĩ Åą       
     !                3    Č      /     *ī É°          %              Ë      >     *+ĩ Éą       
   * +                5    Ė      /     *ī Í°          /              Ï      >     *+ĩ Íą       
   4 5                7    Ð      /     *ī Ņ°          9              Ó      >     *+ĩ Ņą       
   > ?                9    Ô      /     *ī Õ°          C              Ũ      >     *+ĩ Õą       
   H I                ;    Ø      /     *ī Ų°          M              Û      >     *+ĩ Ųą       
   R S                =    Ü      /     *ī Ý°          W              ß      >     *+ĩ Ýą       
   \ ]                ?    ā      /     *ī á°          a              ã      >     *+ĩ áą       
   f g                A    ä      /     *ī å°          k              į      >     *+ĩ åą       
   p q                C    č      /     *ī é°          u              ë      >     *+ĩ éą       
   z {                E    ė      /     *ī í°                        ï      >     *+ĩ íą       
                    G    ð ņ     /     *ī ō°                        ô õ     >     *+ĩ ōą       
                    I J   ö ņ     /     *ī ũ°                        ų õ     >     *+ĩ ũą       
                    P J   ú      /     *ī û°                        ý      >     *+ĩ ûą       
   Ē Ģ                R    þ      /     *ī ĸ°          §                   >     *+ĩ ĸą       
   Ž ­                T         /     *ī°          ą                   >     *+ĩą       
   ķ ·                V    ņ     /     *ī°          ŧ             	 õ     >     *+ĩą       
   Ā Á                X J  
      /     *ī°          Å                   >     *+ĩą       
   Ę Ë                Z         /     *ī°          Ï                   >     *+ĩą       
   Ô Õ                \         /     *ī°          Ų                   >     *+ĩą       
   Þ ß                ^         /     *ī°          ã                   >     *+ĩą       
   č é                `         /     *ī°          í                   >     *+ĩą       
   ō ó                b         /     *ī°          ũ             !      >     *+ĩą       
   ü ý                d   "      /     *ī#°                       %      >     *+ĩ#ą       
                    f   & Ģ     /     *ī'°                       ) §     >     *+ĩ'ą       
                    h   * ņ     /     *ī+°                       - õ     >     *+ĩ+ą       
                    k J  . Ģ     /     *ī/°                       1 §     >     *+ĩ/ą       
   $ %                m   2 Ģ     /     *ī3°          )             5 §     >     *+ĩ3ą       
   . /                p   6 Ģ     /     *ī7°          3             9 §     >     *+ĩ7ą       
   8 9                r   : ņ     /     *ī;°          =             = õ     >     *+ĩ;ą       
   B C                t J  > Ģ     /     *ī?°          G             A §     >     *+ĩ?ą       
   L M                v   B Ģ     /     *īC°          Q             E §     >     *+ĩCą       
   V W                x   FG     /     *īH°          [             JK     >     *+ĩHą       
   ` a                | }  L      /     *īM°          e             O      >     *+ĩMą       
   j k                ~   P      /     *īQ°          o             S      >     *+ĩQą       
   t u                   T      /     *īU°          y             W      >     *+ĩUą       
   ~                    X      /     *īY°                       [      >     *+ĩYą       
                       \G     /     *ī]°                       _K     >     *+ĩ]ą       
                     }  `      /     *īa°                       c      >     *+ĩaą       
                    z   d   e    L f  g  shi jsklemno  M[ spsqsrssstsusvswsxsyszs{