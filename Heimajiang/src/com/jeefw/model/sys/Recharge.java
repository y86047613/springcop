����   3 i  com/jeefw/model/sys/Recharge  +com/jeefw/model/sys/param/RechargeParameter serialVersionUID J ConstantValuei�c� id RuntimeVisibleAnnotations Ljavax/persistence/Id; "Ljavax/persistence/GeneratedValue; Ljavax/persistence/Column; name a_id Ljava/math/BigInteger; a_name Ljava/lang/String; b_id b_name score 
createTime type I func <init> ()V Code
     LineNumberTable LocalVariableTable this Lcom/jeefw/model/sys/Recharge; getId ()J	  ' 
  setId (J)V getA_id ()Ljava/math/BigInteger;	  -   setA_id (Ljava/math/BigInteger;)V 	getA_name ()Ljava/lang/String;	  3   	setA_name (Ljava/lang/String;)V getB_id	  8   setB_id 	getB_name	  <   	setB_name getScore	  @   setScore getCreateTime	  D   setCreateTime getType ()I	  I   setType (I)V getFunc	  N   setFunc 
SourceFile Recharge.java Ljavax/persistence/Entity; Ljavax/persistence/Table; 
rechareges !Lorg/hibernate/annotations/Cache; region all usage 4Lorg/hibernate/annotations/CacheConcurrencyStrategy; 
READ_WRITE 4Lorg/codehaus/jackson/annotate/JsonIgnoreProperties; value 
maxResults firstResult topCount sortColumns cmd queryDynamicConditions sortedConditions dynamicProperties success message sortColumnsString flag !     
           
                s 
            s             s             s             s             s             s             s             s          /     *� �             !        " #    $ %     /     *� &�            A !        " #    ( )     >     *� &�        
    F  G !        " #      
    * +     /     *� ,�            K !        " #    . /     >     *+� ,�        
    P  Q !        " #          0 1     /     *� 2�            U !        " #    4 5     >     *+� 2�        
    Z  [ !        " #          6 +     /     *� 7�            _ !        " #    9 /     >     *+� 7�        
    d  e !        " #          : 1     /     *� ;�            i !        " #    = 5     >     *+� ;�        
    n  o !        " #          > +     /     *� ?�            s !        " #    A /     >     *+� ?�        
    x  y !        " #          B 1     /     *� C�            } !        " #    E 5     >     *+� C�        
    �  � !        " #          F G     /     *� H�            � !        " #    J K     >     *� H�        
    �  � !        " #          L 1     /     *� M�            � !        " #    O 5     >     *+� M�        
    �  � !        " #          P    Q    L  R   S  s T U  Vs W Xe Y Z [  \[ s ]s ^s _s `s as bs cs ds es fs gs h