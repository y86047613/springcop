����   3 ~  com/jeefw/model/sys/Role  'com/jeefw/model/sys/param/RoleParameter serialVersionUID J ConstantValueS�'��. id Ljava/lang/Long; RuntimeVisibleAnnotations Ljavax/persistence/Id; "Ljavax/persistence/GeneratedValue; Ljavax/persistence/Column; name roleKey Ljava/lang/String; role_key length   ( nullable     unique    	roleValue 
role_value description   � permissions Ljava/util/Set; 	Signature #Ljava/util/Set<Ljava/lang/String;>; %Ljavax/persistence/ElementCollection; Ljavax/persistence/JoinTable; role_permission joinColumns Ljavax/persistence/JoinColumn; role_id !Lorg/hibernate/annotations/Cache; region all usage 4Lorg/hibernate/annotations/CacheConcurrencyStrategy; 
READ_WRITE <init> ()V Code
  2 . / LineNumberTable LocalVariableTable this Lcom/jeefw/model/sys/Role; getId ()Ljava/lang/Long;	  : 
  setId (Ljava/lang/Long;)V 
getRoleKey ()Ljava/lang/String;	  @   
setRoleKey (Ljava/lang/String;)V getRoleValue	  E   setRoleValue getDescription	  I   setDescription getPermissions ()Ljava/util/Set; %()Ljava/util/Set<Ljava/lang/String;>;	  O   setPermissions (Ljava/util/Set;)V &(Ljava/util/Set<Ljava/lang/String;>;)V LocalVariableTypeTable equals (Ljava/lang/Object;)Z
 W Y X java/lang/Object Z [ getClass ()Ljava/lang/Class;
 ] _ ^ com/google/common/base/Objects ` a equal '(Ljava/lang/Object;Ljava/lang/Object;)Z obj Ljava/lang/Object; other StackMapTable hashCode ()I
 ] i f j ([Ljava/lang/Object;)I 
SourceFile 	Role.java Ljavax/persistence/Entity; Ljavax/persistence/Table; role 4Lorg/codehaus/jackson/annotate/JsonIgnoreProperties; value 
maxResults firstResult topCount sortColumns cmd queryDynamicConditions sortedConditions dynamicProperties success message sortColumnsString flag !                
                s 
            s  I  Z  Z             s  I  Z             s  I           !    .  "   #  s $ %[ @ &  s ' (  )s * +e , -   . /  0   3     *� 1�    3   
    0  2 4        5 6    7 8  0   /     *� 9�    3       5 4        5 6    ; <  0   >     *+� 9�    3   
    9  : 4        5 6      
    = >  0   /     *� ?�    3       = 4        5 6    A B  0   >     *+� ?�    3   
    A  B 4        5 6          C >  0   /     *� D�    3       E 4        5 6    F B  0   >     *+� D�    3   
    I  J 4        5 6          G >  0   /     *� H�    3       M 4        5 6    J B  0   >     *+� H�    3   
    Q  R 4        5 6          K L       M 0   /     *� N�    3       U 4        5 6    P Q       R 0   P     *+� N�    3   
    Y  Z 4        5 6         S         !   T U  0   �     b+� �*� V+� V� �+� M*� 9,� 9� \� =*� ?,� ?� \� /*� D,� D� \� !*� H,� H� \� *� N,� N� \� ��    3   "    ]  ^  _  `  a  b P c ^ b 4        b 5 6     b b c   J d 6  e   
 � L   f g  0   U     +� WY*� 9SY*� ?SY*� DSY*� HSY*� NS� h�    3       g 4       + 5 6    k    l    L  m   n  s o (  )s * +e , - p  q[ s rs ss ts us vs ws xs ys zs {s |s }