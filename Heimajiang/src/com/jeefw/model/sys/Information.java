����   3 �  com/jeefw/model/sys/Information  .com/jeefw/model/sys/param/InformationParameter serialVersionUID J ConstantValueed��n�4> id Ljava/lang/Long; RuntimeVisibleAnnotations -Lorg/hibernate/search/annotations/DocumentId; Ljavax/persistence/Id; "Ljavax/persistence/GeneratedValue; Ljavax/persistence/Column; name title Ljava/lang/String; (Lorg/hibernate/search/annotations/Field; index (Lorg/hibernate/search/annotations/Index; YES analyzer +Lorg/hibernate/search/annotations/Analyzer; impl :Lorg/apache/lucene/analysis/cn/smart/SmartChineseAnalyzer; store (Lorg/hibernate/search/annotations/Store; length   d nullable     author   ( refreshTime Ljava/util/Date; refresh_time Ljavax/persistence/Temporal; value  Ljavax/persistence/TemporalType; 	TIMESTAMP content columnDefinition LONGTEXT <init> ()V Code
  2 . / LineNumberTable LocalVariableTable this !Lcom/jeefw/model/sys/Information; getId ()Ljava/lang/Long;	  : 
  setId (Ljava/lang/Long;)V getTitle ()Ljava/lang/String;	  @   setTitle (Ljava/lang/String;)V 	getAuthor	  E "  	setAuthor getRefreshTime ()Ljava/util/Date; 1Lorg/codehaus/jackson/map/annotate/JsonSerialize; using !Lcore/support/DateTimeSerializer;	  M $ % setRefreshTime (Ljava/util/Date;)V 
getContent	  R +  
setContent equals (Ljava/lang/Object;)Z
 W Y X java/lang/Object Z [ getClass ()Ljava/lang/Class;
 ] _ ^ com/google/common/base/Objects ` a equal '(Ljava/lang/Object;Ljava/lang/Object;)Z obj Ljava/lang/Object; other StackMapTable hashCode ()I
 ] i f j ([Ljava/lang/Object;)I 
SourceFile Information.java Ljavax/persistence/Entity; Ljavax/persistence/Table; information !Lorg/hibernate/annotations/Cache; region all usage 4Lorg/hibernate/annotations/CacheConcurrencyStrategy; 
READ_WRITE 4Lorg/codehaus/jackson/annotate/JsonIgnoreProperties; 
maxResults firstResult topCount sortColumns cmd queryDynamicConditions sortedConditions dynamicProperties success message sortColumnsString flag *Lorg/hibernate/search/annotations/Indexed; 1Lorg/hibernate/search/annotations/CacheFromIndex; 1Lorg/hibernate/search/annotations/FieldCacheType; CLASS ID !                
                   s 
        3    e   @   c  e     s  I   Z !  "      .    e   @   c  e     s " I #  $ %         s & '  (e ) *  +      .    e   @   c  e     s + ,s -   . /  0   3     *� 1�    3   
    B  D 4        5 6    7 8  0   /     *� 9�    3       G 4        5 6    ; <  0   >     *+� 9�    3   
    K  L 4        5 6      
    = >  0   /     *� ?�    3       O 4        5 6    A B  0   >     *+� ?�    3   
    S  T 4        5 6          C >  0   /     *� D�    3       W 4        5 6    F B  0   >     *+� D�    3   
    [  \ 4        5 6      "    G H       I  Jc K 0   /     *� L�    3       ` 4        5 6    N O  0   >     *+� L�    3   
    d  e 4        5 6      $ %   P >  0   /     *� Q�    3       h 4        5 6    S B  0   >     *+� Q�    3   
    l  m 4        5 6      +    T U  0   �     T+� �*� V+� V� �+� M*� 9,� 9� \� /*� ?,� ?� \� !*� D,� D� \� *� Q,� Q� \� ��    3       p  q  r  s  t  u 4        T 5 6     T b c   < d 6  e   
 � >   f g  0   N     $� WY*� 9SY*� ?SY*� DSY*� QS� h�    3       y 4       $ 5 6    k    l    c  m   n  s o p  qs r se t u v  ([ s ws xs ys zs {s |s }s ~s s �s �s � �   �  ([ e � �e � �