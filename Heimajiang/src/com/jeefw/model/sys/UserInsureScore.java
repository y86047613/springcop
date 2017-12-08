����   3 �  #com/jeefw/model/sys/UserInsureScore  2com/jeefw/model/sys/param/UserInsureScoreParameter serialVersionUID J ConstantValue��L>L��` userId Ljava/lang/Integer; RuntimeVisibleAnnotations Ljavax/persistence/Id; "Ljavax/persistence/GeneratedValue; Ljavax/persistence/Column; name UserID score Ljava/math/BigInteger; Score revenue Revenue insureScore InsureScore winCount WinCount 	lostCount 	LostCount 	drawCount 	DrawCount 	fleeCount 	FleeCount 	userRight 	UserRight masterRight MasterRight masterOrder MasterOrder allLogonTimes AllLogonTimes playTimeCount PlayTimeCount onLineTimeCount OnLineTimeCount lastLogonIP Ljava/lang/String; LastLogonIP lastLogonDate Ljava/util/Date; LastLogonDate Ljavax/persistence/Temporal; value  Ljavax/persistence/TemporalType; DATE lastLogonMachine LastLogonMachine 
registerIP 
RegisterIP registerDate RegisterDate registerMachine RegisterMachine <init> ()V Code
  C ? @ LineNumberTable LocalVariableTable this %Lcom/jeefw/model/sys/UserInsureScore; 	getUserId ()Ljava/lang/Integer;	  K 
  	setUserId (Ljava/lang/Integer;)V 
getRevenue ()Ljava/math/BigInteger;	  Q   
setRevenue (Ljava/math/BigInteger;)V getInsureScore	  V   setInsureScore getWinCount	  Z   setWinCount getLostCount	  ^   setLostCount getDrawCount	  b   setDrawCount getFleeCount	  f   setFleeCount getUserRight	  j !  setUserRight getMasterRight	  n #  setMasterRight getMasterOrder	  r %  setMasterOrder getAllLogonTimes	  v '  setAllLogonTimes getPlayTimeCount	  z )  setPlayTimeCount getOnLineTimeCount	  ~ +  setOnLineTimeCount getLastLogonIP ()Ljava/lang/String;	  � - . setLastLogonIP (Ljava/lang/String;)V getLastLogonDate ()Ljava/util/Date;	  � 0 1 setLastLogonDate (Ljava/util/Date;)V getLastLogonMachine	  � 7 . setLastLogonMachine getRegisterIP	  � 9 . setRegisterIP getRegisterDate	  � ; 1 setRegisterDate getRegisterMachine	  � = . setRegisterMachine getScore	  �   setScore 
SourceFile UserInsureScore.java Ljavax/persistence/Entity; Ljavax/persistence/Table; GameScoreInfo !Lorg/hibernate/annotations/Cache; region all usage 4Lorg/hibernate/annotations/CacheConcurrencyStrategy; 
READ_WRITE 4Lorg/codehaus/jackson/annotate/JsonIgnoreProperties; 
maxResults firstResult topCount sortColumns cmd queryDynamicConditions sortedConditions dynamicProperties success message sortColumnsString flag !                
                s             s             s             s             s             s             s             s    !          s "  #          s $  %          s &  '          s (  )          s *  +          s ,  - .         s /  0 1         s 2 3  4e 5 6  7 .         s 8  9 .         s :  ; 1         s < 3  4e 5 6  = .         s > )  ? @  A   /     *� B�    D        E        F G    H I  A   /     *� J�    D       ^ E        F G    L M  A   >     *+� J�    D   
    b  c E        F G      
    N O  A   /     *� P�    D       f E        F G    R S  A   >     *+� P�    D   
    j  k E        F G          T O  A   /     *� U�    D       n E        F G    W S  A   >     *+� U�    D   
    r  s E        F G          X I  A   /     *� Y�    D       v E        F G    [ M  A   >     *+� Y�    D   
    z  { E        F G          \ I  A   /     *� ]�    D       ~ E        F G    _ M  A   >     *+� ]�    D   
    �  � E        F G          ` I  A   /     *� a�    D       � E        F G    c M  A   >     *+� a�    D   
    �  � E        F G          d I  A   /     *� e�    D       � E        F G    g M  A   >     *+� e�    D   
    �  � E        F G          h I  A   /     *� i�    D       � E        F G    k M  A   >     *+� i�    D   
    �  � E        F G      !    l I  A   /     *� m�    D       � E        F G    o M  A   >     *+� m�    D   
    �  � E        F G      #    p I  A   /     *� q�    D       � E        F G    s M  A   >     *+� q�    D   
    �  � E        F G      %    t I  A   /     *� u�    D       � E        F G    w M  A   >     *+� u�    D   
    �  � E        F G      '    x I  A   /     *� y�    D       � E        F G    { M  A   >     *+� y�    D   
    �  � E        F G      )    | I  A   /     *� }�    D       � E        F G     M  A   >     *+� }�    D   
    �  � E        F G      +    � �  A   /     *� ��    D       � E        F G    � �  A   >     *+� ��    D   
    �  � E        F G      - .   � �  A   /     *� ��    D       � E        F G    � �  A   >     *+� ��    D   
    �  � E        F G      0 1   � �  A   /     *� ��    D       � E        F G    � �  A   >     *+� ��    D   
    �  � E        F G      7 .   � �  A   /     *� ��    D       � E        F G    � �  A   >     *+� ��    D   
    �  � E        F G      9 .   � �  A   /     *� ��    D       � E        F G    � �  A   >     *+� ��    D   
    �  � E        F G      ; 1   � �  A   /     *� ��    D       � E        F G    � �  A   >     *+� ��    D   
    �  � E        F G      = .   � O  A   /     *� ��    D       � E        F G    � S  A   >     *+� ��    D   
    �  � E        F G          �    �    L  �   �  s � �  �s � �e � � �  4[ s �s �s �s �s �s �s �s �s �s �s �s �