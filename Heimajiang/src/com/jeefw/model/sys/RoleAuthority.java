����   3 V  !com/jeefw/model/sys/RoleAuthority  0com/jeefw/model/sys/param/RoleAuthorityParameter serialVersionUID J ConstantValue�?��"�� id Ljava/lang/Long; RuntimeVisibleAnnotations Ljavax/persistence/Id; "Ljavax/persistence/GeneratedValue; Ljavax/persistence/Column; name roleKey Ljava/lang/String; role_key length   ( nullable     menuCode 	menu_code   2 <init> ()V Code
     LineNumberTable LocalVariableTable this #Lcom/jeefw/model/sys/RoleAuthority; getId ()Ljava/lang/Long;	  ' 
  setId (Ljava/lang/Long;)V 
getRoleKey ()Ljava/lang/String;	  -   
setRoleKey (Ljava/lang/String;)V getMenuCode	  2   setMenuCode equals (Ljava/lang/Object;)Z
 7 9 8 java/lang/Object : ; getClass ()Ljava/lang/Class;
 = ? > com/google/common/base/Objects @ A equal '(Ljava/lang/Object;Ljava/lang/Object;)Z obj Ljava/lang/Object; other StackMapTable hashCode ()I
 = I F J ([Ljava/lang/Object;)I 
SourceFile RoleAuthority.java Ljavax/persistence/Entity; Ljavax/persistence/Table; role_authority !Lorg/hibernate/annotations/Cache; region all usage 4Lorg/hibernate/annotations/CacheConcurrencyStrategy; 
READ_WRITE !                
                s 
            s  I  Z             s  I  Z  	        3     *� �        
    #  % !        " #    $ %     /     *� &�            ( !        " #    ( )     >     *+� &�        
    ,  - !        " #      
    * +     /     *� ,�            0 !        " #    . /     >     *+� ,�        
    4  5 !        " #          0 +     /     *� 1�            8 !        " #    3 /     >     *+� 1�        
    <  = !        " #          4 5     �     F+� �*� 6+� 6� �+� M*� &,� &� <� !*� ,,� ,� <� *� 1,� 1� <� ��            @  A  B  C  D  E !        F " #     F B C   . D #  E   
 � 0   F G     G     � 7Y*� &SY*� ,SY*� 1S� H�            I !        " #    K    L      M   N  s O P  Qs R Se T U