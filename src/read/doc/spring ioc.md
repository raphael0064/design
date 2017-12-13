#IOC
spring ioc是spring的核心之一，也是spring体系的基础，那么spring ioc所依赖的底层技术是什么的？反射，以前我们开发程序的时候对象之间的相互调用需要用new来实现，现在所有的bean都是通过spring容器来管理。这样做有什么好处呢？解耦！以前程序直接的调用用new直接给写死了，现在我们可以通过注入不同的接口实现类来完成对象直接的调用。

 

###首先来聊聊Java的反射机制

1、反射机制的作用：

  反编译：.class-->.java

  通过反射机制访问java对象的属性，方法，构造方法等；

2、Java反射机制用途：

  在运行时判断任意一个对象所属的类

  在运行时构造任意一个类的对象

  在运行时判断任意一个类所具有的成员变量和方法

  在运行时调用任意一个对象的方法

3、sun为我们提供了那些反射机制中的类：
```
java.lang.Class;
java.lang.reflect.Constructor;
java.lang.reflect.Field;
java.lang.reflect.Method;
java.lang.reflect.Modifier;
```
  

4、反射实现的方式

```
Class c=Class.forName("className");
注明：className必须为全名，也就是得包含包名，比如，cn.xx.UserInfo;

Object obj=c.newInstance();
//创建对象的实例 

Constructor getConstructor(Class[] params)
//根据指定参数获得public构造器 

Constructor[] getConstructors()
//获得public的所有构造器 

Constructor getDeclaredConstructor(Class[] params)
//根据指定参数获得public和非public的构造器 

Constructor[] getDeclaredConstructors()
//获得public的所有构造器 

ewInstance();
//创建对象的实例 

获得类方法的方法
Method getMethod(String name, Class[] params),
根据方法名，参数类型获得方法 

Method[] getMethods()
//获得所有的public方法 

Method getDeclaredMethod(String name, Class[] params)
//根据方法名和参数类型，获得public和非public的方法 

Method[] getDeclaredMethods()
//获得所以的public和非public方法

获得类中属性的方法
Field getField(String name)
//根据变量名得到相应的public变量 

Field[] getFields()
//获得类中所以public的方法 

Field getDeclaredField(String name)
//根据方法名获得public和非public变量 

Field[] getDeclaredFields()
//获得类中所有的public和非public方法   
```

 

总结一句,以前写的代码，类的熟悉、方法什么东西的都固定了，如果使用反射我们就可以在运行的时候动态的去修改、增删对象的熟悉、方法等等。

 

spring IOC是如果使用反射来完成对象的注入呢？

1、读取配置文件，或者扫描注解属性

2、根据配置文件，通过反射实例化对象

3、给对象注入依赖的属性

4、放到类似hashMap结构中，供系统调用

 
```
/**
* 学习版容器
*
*/
public class LeamClassPathXMLApplicationContext {
    private List<Definition> beanDefines = new ArrayList<Definition>();
    private Map<String, Object> sigletons = new HashMap<String, Object>();

    

    public LeamClassPathXMLApplicationContext(String filename){
        this.readXML(filename);
        this.instanceBeans();
        this.injectObject();
    }

    /**
     * 为bean对象的属性注入值
     */
    private void injectObject() {
        for(Definition beanDefinition : beanDefines){
           Object bean = sigletons.get(beanDefinition.getId());
            if(bean!=null){
                try {
                    PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
                    for(ProsDefinition propertyDefinition : beanDefinition.getPropertys()){
                        for(PropertyDescriptor properdesc : ps){
                            if(propertyDefinition.getName().equals(properdesc.getName())){
                        Method setter = properdesc.getWriteMethod();//获取属性的setter方法 
                               if(setter!=null){
                                    Object value = sigletons.get(propertyDefinition.getRef());
                                    setter.setAccessible(true);
                                    setter.invoke(bean, value);//把引用对象注入到属性                                }
                               break;
                           }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * 完成bean的实例化
     */
    private void instanceBeans() {
        for(Definition beanDefinition : beanDefines){
            try {
                if(beanDefinition.getClassName()!=null && !"".equals(beanDefinition.getClassName().trim()))
                   sigletons.put(beanDefinition.getId(), 
                   Class.forName(beanDefinition.getClassName()).newInstance());
            } catch (Exception e) {
               e.printStackTrace();
            }
        }       
    }

    /**
     * 读取xml配置文件
     * @param filename
     */
    private void readXML(String filename) {
           SAXReader saxReader = new SAXReader();   
            Document document=null;   
          try{
           URL xmlpath = this.getClass().getClassLoader().getResource(filename);
           document = saxReader.read(xmlpath);
           Map<String,String> nsMap = new HashMap<String,String>();
           nsMap.put("ns","http://www.springframework.org/schema/beans");//加入命名空间
             XPath xsub = document.createXPath("//ns:beans/ns:bean");//创建beans/bean查询路径
             xsub.setNamespaceURIs(nsMap);//设置命名空间
             List<Element> beans = xsub.selectNodes(document);//获取文档下所有bean节点 
             for(Element element: beans){
              String id = element.attributeValue("id");//获取id属性值
                String clazz = element.attributeValue("class"); //获取class属性值        
                Definition beanDefine = new Definition(id, clazz);
               XPath propertysub =  element.createXPath("ns:property");
               propertysub.setNamespaceURIs(nsMap);//设置命名空间
                  List<Element> propertys = propertysub.selectNodes(element);
               for(Element property : propertys){                    
                    String propertyName = property.attributeValue("name");//元素内部引用的属性也获取
                    String propertyref = property.attributeValue("ref");
                 ProsDefinition propertyDefinition = new ProsDefinition(propertyName, propertyref);
                    beanDefine.getPropertys().add(propertyDefinition);
                }
                beanDefines.add(beanDefine);
             } 
            }catch(Exception e){   
                e.printStackTrace();
            }
    }

    /**

     * 获取bean实例
     * @param beanName
     * @return
     */
    public Object getBean(String beanName){
        return this.sigletons.get(beanName);
    }

}
```

###spring ioc核心思想

ioc的思想最核心的地方在于，资源不由使用资源的双方管理，而由不使用资源的第三方管理，这可以带来很多好处。
第一，资源集中管理，实现资源的可配置和易管理。
第二，降低了使用资源双方的依赖程度，也就是我们说的耦合度。