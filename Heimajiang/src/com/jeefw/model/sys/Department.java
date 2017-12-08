����   3 r  com/jeefw/model/sys/Department  -com/jeefw/model/sys/param/DepartmentParameter serialVersionUID J ConstantValue��w[�f id Ljava/lang/Long; RuntimeVisibleAnnotations Ljavax/persistence/Id; "Ljavax/persistence/GeneratedValue; Ljavax/persistence/Column; name departmentKey Ljava/lang/String; department_key length    nullable     unique    departmentValue department_value   ( parentDepartmentkey parent_departmentkey description   � <init> ()V Code
  % ! " LineNumberTable LocalVariableTable this  Lcom/jeefw/model/sys/Department; getId ()Ljava/lang/Long;	  - 
  setId (Ljava/lang/Long;)V getDepartmentKey ()Ljava/lang/String;	  3   setDepartmentKey (Ljava/lang/String;)V getDepartmentValue	  8   setDepartmentValue getParentDepartmentkey	  <   setParentDepartmentkey getDescription	  @   setDescription equals (Ljava/lang/Object;)Z
 E G F java/lang/Object H I getClass ()Ljava/lang/Class;
 K M L com/google/common/base/Objects N O equal '(Ljava/lang/Object;Ljava/lang/Object;)Z obj Ljava/lang/Object; other StackMapTable hashCode ()I
 K W T X ([Ljava/lang/Object;)I 
SourceFile Department.java Ljavax/persistence/Entity; Ljavax/persistence/Table; 
department !Lorg/hibernate/annotations/Cache; region all usage 4Lorg/hibernate/annotations/CacheConcurrencyStrategy; 
READ_WRITE 4Lorg/codehaus/jackson/annotate/JsonIgnoreProperties; value 
maxResults firstResult topCount sortColumns cmd queryDynamicConditions sortedConditions dynamicProperties success message sortColumnsString flag !                
                s 
            s  I  Z  Z             s  I  Z             s  I             s  I     ! "  #   3     *� $�    &   
    )  + '        ( )    * +  #   /     *� ,�    &       . '        ( )    . /  #   >     *+� ,�    &   
    2  3 '        ( )      
    0 1  #   /     *� 2�    &       6 '        ( )    4 5  #   >     *+� 2�    &   
    :  ; '        ( )          6 1  #   /     *� 7�    &       > '        ( )    9 5  #   >     *+� 7�    &   
    B  C '        ( )          : 1  #   /     *� ;�    &       F '        ( )    = 5  #   >     *+� ;�    &   
    J  K '        ( )          > 1  #   /     *� ?�    &       N '        ( )    A 5  #   >     *+� ?�    &   
    R  S '        ( )          B C  #   �     b+� �*� D+� D� �+� M*� ,,� ,� J� =*� 2,� 2� J� /*� 7,� 7� J� !*� ;,� ;� J� *� ?,� ?� J� ��    &   "    V  W  X  Y  Z  [ B \ ^ [ '        b ( )     b P Q   J R )  S   
 � L   T U  #   U     +� EY*� ,SY*� 2SY*� 7SY*� ;SY*� ?S� V�    &       ` '       + ( )    Y    Z    L  [   \  s ] ^  _s ` ae b c d  e[ s fs gs hs is js ks ls ms ns os ps q