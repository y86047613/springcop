����   3 U  com/jeefw/model/sys/GameStatus  ,com/jeefw/model/sys/param/UserScoreParameter serialVersionUID J ConstantValuei�c� 
statusName Ljava/lang/String; RuntimeVisibleAnnotations Ljavax/persistence/Id; "Ljavax/persistence/GeneratedValue; Ljavax/persistence/Column; name 
StatusName statusValue Ljava/lang/Integer; StatusValue statusString StatusString 	StatusTip statusDescription StatusDescription <init> ()V Code
     LineNumberTable LocalVariableTable this  Lcom/jeefw/model/sys/GameStatus; getStatusName ()Ljava/lang/String;	  & 
  setStatusName (Ljava/lang/String;)V getStatusValue ()Ljava/lang/Integer;	  ,   setStatusValue (Ljava/lang/Integer;)V getStatusString	  1   setStatusString getStatusTip	  5   setStatusTip 	statusTip getStatusDescription	  :   setStatusDescription 
SourceFile GameStatus.java Ljavax/persistence/Entity; Ljavax/persistence/Table; SystemStatusInfo !Lorg/hibernate/annotations/Cache; region all usage 4Lorg/hibernate/annotations/CacheConcurrencyStrategy; 
READ_WRITE 4Lorg/codehaus/jackson/annotate/JsonIgnoreProperties; value 
maxResults firstResult topCount sortColumns cmd queryDynamicConditions sortedConditions dynamicProperties success message sortColumnsString flag !                
                s             s             s             s             s          /     *� �                     ! "    # $     /     *� %�           .          ! "    ' (     >     *+� %�       
    2  3          ! "      
    ) *     /     *� +�           6          ! "    - .     >     *+� +�       
    :  ;          ! "          / $     /     *� 0�           >          ! "    2 (     >     *+� 0�       
    B  C          ! "          3 $     /     *� 4�           F          ! "    6 (     >     *+� 4�       
    J  K          ! "      7    8 $     /     *� 9�           N          ! "    ; (     >     *+� 9�       
    R  S          ! "          <    =    L  >   ?  s @ A  Bs C De E F G  H[ s Is Js Ks Ls Ms Ns Os Ps Qs Rs Ss T