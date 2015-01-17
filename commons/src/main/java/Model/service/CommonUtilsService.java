package Model.service;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import Model.others.Filter;
import Model.others.FilterNode;
import Model.others.ObjectFieldContainer;
import Model.entity.*;
import org.apache.commons.beanutils.PropertyUtils;

import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;


public class CommonUtilsService {
	

	public static void castFilterTypes(Filter n){
		
		
		List<Object> castedvalues = new ArrayList<Object>();
		try {
			castfilter("Model."+n.getClassname(), n.getPropfilters(), castedvalues);
	
		if(n.getColadmintype()!=null && n.getCompositefilter() && !n.getJoinfilters().isEmpty()){
			castfilter(n.getColadmintype(), n.getJoinfilters(), castedvalues);
		}
		
		
		
		
		
		} catch (NoSuchFieldException e) {

			e.printStackTrace();
		} catch (SecurityException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}

	
	public static ServiceImpl getService(String adminclassname, ApplicationContext appContext) throws ClassNotFoundException{
		Object adminserv = new Object();
		Reflections reflect = new Reflections("Model");
		Class adminentity = Class.forName("Model."+adminclassname);
		Set<Class<? extends ServiceImpl>> services = reflect.getSubTypesOf(ServiceImpl.class);
		
		
		for(Class d: services){
			System.out.println(d.getName());
			
			ParameterizedType tp = (ParameterizedType) d.getGenericSuperclass();
			Type[] generics = tp.getActualTypeArguments();
			for(int i =0;i<generics.length;i++){
				
				Class cl = (Class)generics[i];
				System.out.println(cl.getName()+"   "+adminclassname);
				if(cl.getName().equals("Model."+adminclassname)){
					System.out.println("ВОТ ОН КЛАСС "+cl.getName()+"   "+adminclassname);
					adminserv = appContext.getBean(d);
			        return (ServiceImpl) adminserv;

					
				}
			}
			
			
			
		}
		return null;
	}

	private static void castfilter(String classname, Map<String, Set<FilterNode>> map, List<Object> castedvalues) throws NoSuchFieldException, SecurityException, ClassNotFoundException, ParseException{
		
		Class joinclass = Class.forName(classname);
		for(Entry<String, Set<FilterNode>> jj: map.entrySet()){
			Field f = joinclass.getDeclaredField(jj.getKey());
			Class ftype = f.getType();
			System.out.println("Тип "+f.getType().getName());
			String ftypename = ftype.getName();
			System.out.println("Тип данных!!!!!!!!!!!!!!!!!!!!!!!!! "+ftypename);
			System.out.println("Старые типы  ");
			for(FilterNode flnd: jj.getValue()){
				for(Object obj: flnd.getValues()){
					if(obj!=null)
					System.out.println(obj.getClass().getName());
					else System.out.println("null");
						
				}
				
			}
			for(FilterNode flnd: jj.getValue()){
				castedvalues = new ArrayList<Object>();
				
				for(Object obj: flnd.getValues()){
					System.out.println("Тип данных: "+ftypename);
					
					
						if(obj!=null && !obj.toString().toLowerCase().equals("null") && !obj.toString().toLowerCase().equals("")){
							
							switch(ftypename){
							
							case "java.lang.String":
								System.out.println("ADD STRING");
								castedvalues.add(obj);
								break;
							case "java.util.Date":
								System.out.println("Дата");
								Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).parse(obj.toString());
								castedvalues.add(date);
								break;
							case "java.lang.Integer":
								System.out.println("ADD INTEGER");
								Integer val1 = Integer.parseInt(obj.toString());
								castedvalues.add(val1);
								break;
							case "java.lang.Double":
								System.out.println("ADD DOUBLE");
								Double val2 = Double.parseDouble(obj.toString());
								castedvalues.add(val2);
								break;
							case "java.lang.Boolean":
								System.out.println("ADD BOOLEAN");
								Boolean val3 = Boolean.parseBoolean(obj.toString());
								castedvalues.add(val3);
								break;
							default:castedvalues.add(obj);break;
							}
					
						}
						else{
							castedvalues.add(null);
						}
					
				}
				flnd.getValues().clear();
				for(Object obj: castedvalues){
					flnd.getValues().add(obj);
				}
			}
			
			System.out.println("Новые типы  ");
			for(FilterNode flnd: jj.getValue()){
				for(Object obj: flnd.getValues()){
					if(obj!=null)
					System.out.println(obj.getClass().getName());
					else System.out.println("null");
				}
				
			}
		}
		
	}
	
	public static Object getValue(Object obj, Field field) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		System.out.println("rrrrrrrrrrrrrrrrrrr");
		Field[] props = obj.getClass().getDeclaredFields();
		
		for(Field fld:props){
			if(fld.equals(field)){
				return PropertyUtils.getProperty(obj, fld.getName());
			}
		}
		
		throw new IllegalArgumentException("У класса "+obj.getClass().getName()+" нет такого свойства");
	}
	
	
	public static Object getValueByName(Object obj, String fieldname) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Field[] props = obj.getClass().getDeclaredFields();
		System.out.println("Свойство "+fieldname);
		for(Field fld:props){
			if(fld.getName().equals(fieldname)){
				return PropertyUtils.getProperty(obj, fieldname);
			}
		}
		
		throw new IllegalArgumentException("У класса "+obj.getClass().getName()+" нет такого свойства "+fieldname);
	}
	
	public static Class getClassByName(Object obj, String fieldname) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Field[] props = obj.getClass().getDeclaredFields();
		System.out.println("Свойство "+fieldname);
		for(Field fld:props){
			if(fld.getName().equals(fieldname)){
				return fld.getType();
			}
		}
		
		throw new IllegalArgumentException("У класса "+obj.getClass().getName()+" нет такого свойства "+fieldname);
	}
	
	public static Class getFieldClassByClass(Class cclass, String fieldname) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Field[] props = cclass.getDeclaredFields();
		System.out.println("Свойство "+fieldname);
		for(Field fld:props){
			if(fld.getName().equals(fieldname)){
				return fld.getType();
			}
		}
		
		throw new IllegalArgumentException("У класса "+cclass.getName()+" нет такого свойства "+fieldname);
	}
	
	
	
    public static Field[] getAnnotatedDeclaredFields(Class clazz, Class<? extends Annotation> annotationClass,
            boolean recursively) {
    	Field[] allFields = clazz.getDeclaredFields();
    	List<Field> annotatedFields = new LinkedList<Field>();

    		for (Field field : allFields) {
			if(field.isAnnotationPresent(annotationClass))
					annotatedFields.add(field);
    		}

    		return annotatedFields.toArray(new Field[annotatedFields.size()]);
    }
    
    
    public static Field getIdField(Class <? extends Entity> clazz) throws IntrospectionException {
    	Field[] allFields = clazz.getDeclaredFields();
 
    		for (Field field : allFields) {
    			Method getter = new PropertyDescriptor(field.getName(), field.getDeclaringClass()).getReadMethod();
			if(getter.isAnnotationPresent(javax.persistence.Id.class))
			{
					return field;
					}
    		}
    		

    		throw new IllegalArgumentException("У класса "+clazz.getName()+" нет ID ");
    }
	
	public static void setEntityAdminReferences(ObjectFieldContainer container) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Object ent = container.getObj(); 
		Object entid = new Object();
		if(!(ent instanceof Role)){
			 entid = CommonUtilsService.getValueByName(ent, "id");
		}
	
			
		
		if(container.getObj() instanceof Category){
			container.getRefs().put("Показать на сайте", "/category/"+entid+"/pagin/1");
			container.getRefs().put("Редактировать", "/admin/admincategory/"+entid+"");
			container.getRefs().put("Товары", "/admin/admincategory/"+entid+"/list/goods/pagin/1");
			container.getRefs().put("Новости", "/admin/admincategory/"+entid+"/list/news/pagin/1");
			container.getRefs().put("Подкатегории", "/admin/admincategory/"+entid+"/list/subcategs/pagin/1");
			container.getRefs().put("Удалить", "/admin/setdeleted/Category/"+entid);
		}
		
			
		if(container.getObj() instanceof News){
		container.getRefs().put("Показать на сайте", "/category/"+((Category)CommonUtilsService.getValueByName(ent, "category")).getId()+"/news/"+entid);
		container.getRefs().put("Редактиовать", "/admin/adminnews/"+((Category)CommonUtilsService.getValueByName(ent, "category")).getId()+"/updatenews/"+entid);
		container.getRefs().put("Удалить", "/admin/setdeleted/News/"+entid);
		}
		
		if(container.getObj() instanceof Order){
		container.getRefs().put("Развернуть позиции", "/admin/adminorders/"+entid+"/list/items/pagin/1");
		container.getRefs().put("Номера телефонов", "/admin/adminorders/"+entid+"/list/phones/pagin/1");
		container.getRefs().put("Удалить заказ", "/admin/setdeleted/Order/"+entid);
		}
		
		if(container.getObj() instanceof GoodItem){
		container.getRefs().put("Показать на сайте", "/good/"+entid+"/all");
		container.getRefs().put("Редактиовать", "/admin/admingood/"+entid+"/updateGood");
		container.getRefs().put("Удалить", "/admin/setdeleted/GoodItem/"+entid);
		}
		
		if(container.getObj() instanceof User){

			container.getRefs().put("Вести роли", "/admin/adminusers/"+entid+"/updateuserroles");
			container.getRefs().put("Комментарии пользователя", "/admin/adminusers/"+entid+"/updateusercomments");
			container.getRefs().put("Информация пользователя", "/admin/adminusers/"+entid+"/updateuser");
			container.getRefs().put("Удалить", "/admin/setdeleted/User/"+entid);
			
		}
		
		if(container.getObj() instanceof Role){
			entid = CommonUtilsService.getValueByName(ent, "authority");
			container.getRefs().put("Редактировать", "/admin/updaterole/"+entid);
			container.getRefs().put("Удалить", "/admin/setdeleted/Role/"+entid);
			
		}
		
		if(container.getObj() instanceof DeliveryType){
			container.getRefs().put("Редактировать", "/admin/updatedeltype/"+entid);
			container.getRefs().put("Удалить", "/admin/setdeleted/DeliveryType/"+entid);
			
		}
		
		if(container.getObj() instanceof PayType){
			container.getRefs().put("Редактировать", "/admin/updatepaytype/"+entid);
			container.getRefs().put("Удалить", "/admin/setdeleted/PayType/"+entid);
			
		}
		
		if(container.getObj() instanceof Action){
			container.getRefs().put("Редактировать", "/admin/adminactions/updateaction/"+entid);
			container.getRefs().put("Удалить", "/admin/setdeleted/Action/"+entid);
			
		}
		
		if(container.getObj() instanceof PageGroup){
			container.getRefs().put("Редактировать", "/admin/admincontent/updatePagegroup/"+entid);
			container.getRefs().put("Удалить", "/admin/setdeleted/PageGroup/"+entid);
			
		}
		
		if(container.getObj() instanceof Guarantie){
			container.getRefs().put("Редактировать", "/admin/admincontent/updateGuarantie/"+entid);
			container.getRefs().put("Удалить", "/admin/setdeleted/Guarantie/"+entid);
			
		}
		
		if(container.getObj() instanceof GoodStatus){
			container.getRefs().put("Редактировать", "/admin/admincontent/updateGoodstatus/"+entid);
			container.getRefs().put("Удалить", "/admin/setdeleted/GoodStatus/"+entid);
			
		}
		
		if(container.getObj() instanceof Producer){
			container.getRefs().put("Редактировать", "/admin/admincontent/updateProducer/"+entid);
			container.getRefs().put("Удалить", "/admin/setdeleted/Producer/"+entid);
			
		}
		
		if(container.getObj() instanceof Page){
			container.getRefs().put("Редактировать", "/admin/admincontent/updateInfopage/"+entid);
			container.getRefs().put("Удалить", "/admin/setdeleted/Page/"+entid);
			
		}
		
		if(container.getObj() instanceof Configuration){
			container.getRefs().put("Редактировать", "/admin/admincontent/updateConfiguration/"+entid);
			container.getRefs().put("Удалить", "/admin/setdeleted/Configuration/"+entid);
			
		}
		
		if(container.getObj() instanceof NewsType){
			container.getRefs().put("Редактировать", "/admin/admincontent/updateNewstype/"+entid);
			container.getRefs().put("Удалить", "/admin/setdeleted/NewsType/"+entid);
			
		}
		
	}


	public static Field getForeignKeyByTypeName(String adminclassname, String coladmintype) throws ClassNotFoundException, IntrospectionException {
		Class adminclazz = Class.forName("Model."+adminclassname);
		Field[]flds = adminclazz.getDeclaredFields();
		for(Field fld:flds){
			if(coladmintype.equals(fld.getType().getSimpleName())){
				Method getter = new PropertyDescriptor(fld.getName(), fld.getDeclaringClass()).getReadMethod();
				if(getter.isAnnotationPresent(javax.persistence.JoinColumn.class)){
					return fld;
				}
			}
		}
		throw new IllegalArgumentException("У класса "+adminclassname+" нет внешнего ключа с типом "+coladmintype);
	}
	
	public static Object getForeignKeyValueByTypeName(Object adminobject, String coladmintype) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Class adminclazz = adminobject.getClass();
		Field[]flds = adminclazz.getDeclaredFields();
		System.out.println(coladmintype);
		for(Field fld:flds){
			if(coladmintype.equals(fld.getType().getSimpleName())){
				Method getter = new PropertyDescriptor(fld.getName(), fld.getDeclaringClass()).getReadMethod();
				if(getter.isAnnotationPresent(javax.persistence.JoinColumn.class)){
					return PropertyUtils.getProperty(adminobject, fld.getName());
				}
			}
		}
		throw new IllegalArgumentException("У класса "+adminobject.getClass().getSimpleName()+" нет внешнего ключа с типом "+coladmintype);
	}
	
	
	
	public static String getJoinCollectionFieldName(String adminclassname, String coladmintype) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Class adminclazz = Class.forName("Model."+adminclassname);
		Class coladminclazz = Class.forName("Model."+coladmintype);
		Field[]flds = adminclazz.getDeclaredFields();
		
		Reflections reflect = new Reflections("Model");
		Class adminentity = Class.forName("Model."+adminclassname);
		Set<Class> services = new TreeSet<Class>();
		
		for(Field fld: flds){
			System.out.println("ПОЛЕ КЛАССА "+fld.getDeclaringClass().getSimpleName()+" НАЗВАНИЕ "+fld.getName()+" ТИП "+fld.getType().getSimpleName()+" КОЛЛЕКЦИЯ "+Collection.class.isAssignableFrom(fld.getType()));
			if(Collection.class.isAssignableFrom(fld.getType())){
				ParameterizedType type = (ParameterizedType) fld.getGenericType();
		        Class<?> cl = (Class<?>) type.getActualTypeArguments()[0];
				
						if(coladminclazz.equals(cl)){
							return fld.getName();
						}

			

			
			}
		}

		throw new IllegalArgumentException("У класса "+adminclazz.getClass().getSimpleName()+" нет внешнего ключа с типом "+coladmintype);
	


		}
	
	
	
}
