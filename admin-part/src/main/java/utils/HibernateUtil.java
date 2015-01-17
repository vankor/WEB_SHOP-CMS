package utils;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Model.Action;
import Model.Adress;
import Model.Adress_Delivery;
import Model.AnonimEmailWatcher;
import Model.BasicConfiguration;
import Model.Bucket;
import Model.Category;
import Model.Comment;
import Model.Country;
import Model.DeliveryType;
import Model.EntityField;
import Model.GoodItem;
import Model.GoodState;
import Model.GoodStatus;
import Model.Good_Guarantie;
import Model.Guarantie;
import Model.Image;
import Model.News;
import Model.NewsType;
import Model.Order;
import Model.OrderRow;
import Model.Page;
import Model.PageGroup;
import Model.PayType;
import Model.PhoneNumber;
import Model.Producer;
import Model.PropSegment;
import Model.Property;
import Model.Region;
import Model.Role;
import Model.Slider;
import Model.SliderImage;
import Model.TextPart;
import Model.Town;
import Model.Town_Delivery;
import Model.User;
import Model.Value;
import Model.Video;
import Model.Vote;
import Model.filters.AdminFormat;
import Model.filters.FieldPriority;
import Model.filters.PropRangeFilter;
import Model.filters.PropValueFilter;


public class HibernateUtil {
	private static volatile HibernateUtil instance;
	private final SessionFactory concreteSessionFactory;
	
	public Session getSession()
			throws HibernateException {
		return concreteSessionFactory.openSession();
	}
	
	private HibernateUtil(String dburl, String username, String password, String dialect, String driver){
		
		try {
			Properties prop= new Properties();
			prop.setProperty("hibernate.connection.url", dburl);
			prop.setProperty("hibernate.connection.username", username);
			prop.setProperty("hibernate.connection.password", password);
			prop.setProperty("hibernate.dialect", dialect);
			prop.setProperty("hibernate.connection.driver_class", driver);
			prop.setProperty("hibernate.hbm2ddl.auto", "update");
			prop.setProperty("hibernate.cache.use_second_level_cache", "true");
			prop.setProperty("hibernate.cache.use_query_cache", "true");
	//		prop.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
			prop.setProperty("hibernate.cache.provider_class", "net.sf.ehcache.hibernate.SingletonEhCacheProvider");
	//		prop.setProperty("hibernate.hbm2ddl.import_files", "classpath:/import.sql");
			
			concreteSessionFactory = new Configuration()
		   .addPackage("Model").addPackage("Model.filters")
				   .addProperties(prop)
				   .addAnnotatedClass(User.class)
				   .addAnnotatedClass(GoodItem.class)
				   .addAnnotatedClass(Comment.class)
				   .addAnnotatedClass(Bucket.class)
				   .addAnnotatedClass(Order.class)
				   .addAnnotatedClass(OrderRow.class)
				   .addAnnotatedClass(Category.class)
				   .addAnnotatedClass(Property.class)
				   .addAnnotatedClass(Value.class)
				   .addAnnotatedClass(Image.class)
				   .addAnnotatedClass(Video.class)
				   .addAnnotatedClass(GoodState.class)
				   .addAnnotatedClass(Vote.class)
				   .addAnnotatedClass(Role.class)
				   .addAnnotatedClass(AnonimEmailWatcher.class)
				   .addAnnotatedClass(PhoneNumber.class)
				   .addAnnotatedClass(PropSegment.class)
				   .addAnnotatedClass(Country.class)
				   .addAnnotatedClass(Region.class)
				   .addAnnotatedClass(Town.class)
				   .addAnnotatedClass(DeliveryType.class)
				   .addAnnotatedClass(PayType.class)
				   .addAnnotatedClass(Adress.class)
				   .addAnnotatedClass(News.class)
				   .addAnnotatedClass(Action.class)
				   .addAnnotatedClass(TextPart.class)
				   .addAnnotatedClass(Town_Delivery.class)
				   .addAnnotatedClass(EntityField.class)
				   .addAnnotatedClass(Page.class)
				   .addAnnotatedClass(NewsType.class)
				   .addAnnotatedClass(Configuration.class)
				   .addAnnotatedClass(Producer.class)
				   .addAnnotatedClass(PageGroup.class)
				   .addAnnotatedClass(Guarantie.class)
				   .addAnnotatedClass(GoodStatus.class)
				   .addAnnotatedClass(Good_Guarantie.class)
				   .addAnnotatedClass(Slider.class)
				   .addAnnotatedClass(SliderImage.class)
				   .addAnnotatedClass(Adress_Delivery.class)
				   .addAnnotatedClass(AdminFormat.class)
				   .addAnnotatedClass(FieldPriority.class)
				   .addAnnotatedClass(PropRangeFilter.class)
				   .addAnnotatedClass(PropValueFilter.class)
				   .addAnnotatedClass(BasicConfiguration.class)
				   .buildSessionFactory();
		
			
		
		
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
		
	}
	
	public static HibernateUtil getInstance(String dburl, String username, String password, String dialect, String driver){
		HibernateUtil localInstance = instance;
		if(localInstance==null){
			synchronized(HibernateUtil.class){
				if(localInstance==null){
					localInstance = new HibernateUtil(dburl,username, password, dialect, driver);
					
				}
				
			}
			
		}
		return localInstance;
	}
	

	}