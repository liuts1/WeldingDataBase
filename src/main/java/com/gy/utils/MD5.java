package com.gy.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * @author yhm
 */
public class MD5 {
	private static String [] encodingAddtion = new String[]{"!","@","#","$","%","^","&","*","(",")","_","+","=","d","b","n"};
	private static String str1 = "1.items.xml文件的路径是每个extension的resource文件夹，文件命名有规范，core-item.xml2.item.xml文件是用xsd约束来规范书写的，输入不符合规范的数据会报错3.item.xml文件中的itemtype的定义是有顺序的，被继承的要往前放4.两种方式为一个已存在的itemtype新增属性，	第一种是创建一个继承他的子类型，然后为子类型添加属性，子类型的 autocreate 属性必须是true，否则会创建失败；generate属性也必须为true，否则组件不会创建Java class类文件,也就不能使用getter和setter方法	第二种是：直接为当前itemtype添加属性，这种方式是不被鼓励使用的，需要自己添加getset方法到extension Manager5.重新定义属性的一些特型，比如设置唯一标识 只读 甚至完全重写等，之后这个属性仅限于子type使用6.为自定义类型新增列：通过更新hybris来对一个在数据库中已存在的types进行更改是不可行的，列的默认类型是string（MySQL chooses VARCHAR(255)），但是我们可以在*-items.xml中的	<columntype> 标签中设置其他的类型。列的类型也可以按照数据库区分7.惰性加载是在实例化对象的时候不是立刻就设置对象的全部属性值。Model的加载原理就是惰性加载　　一旦你加载一个Model,他包含所有的原始值。然而,在这种状态下,关系还未填写。一旦你通过调用相应的getter访问这样一种关系,这种关系是按需加载。这意味着一旦你加载一个模型,你需要担心加载任何依赖模型。型中的相应阶段的生命周期包括:Instantiating the Model　　   这可以通过创建一个新的模型实例或从数据库中加载模型。新建一个实例　　　　　　　　  这可以通过两种方式通过它的构造函数。ModelService通过工厂方法。从数据库中加载一个现有的模型可以通过使用pk或使用一个查询表达式。See section Loading a Model below.Modifying Model Values  若需要：Set the properties of a ModelSaving Model Values  如果新建或者修改Removing the model  如果不再需要模型,数据库记录被删除。可以在Model的生命周期内加入拦截器Models中的属性都是默认基于配置中的属性自动生成getter，setter方法。你可以在生成的过程中指定不需要哪个属性，或者执行所有的属性都不要那么就不会生成这个type对应的Model生成任何一种类型的Model，都遵循下面的规则：  1.model的名称为生成的类型加上Model后缀。比如，生成Product 的Model名称为ProductModel  2.还将为Model生成一个类似的包名：　　　　字符串model 将最为包名的一部分被加到扩展的跟节点后面，而jalo 将被从包名中去除　　　　例如：de.hybris.platform.europe1.jalo.TaxRow 对应的Model为 de.hybris.platform.europe1.model.TaxRowModel.   3.所有属性类型都是private的　4.会自动为所有的属性创建getter，setter方法在hybris系统构建的过程中非常早期的时候Models就已经生成了。ModelServiceModelService 是处理Model生命周期内的所有操作的service。可以通过modelService 在spring中的ID或者继承 de.hybris.platform.servicelayer.model.ModelService接口得到ModelService。主要任务包含如下几点：通过PK加载Models通过item加载Models新建Models更新Models删除Modelstype系统1.types可以管理对象的属性并存储到数据库，生成对应的数据库表结构，还有对应的Java类实现2.Type = type definition in items.xml + its Java implementation3.The Item type is the supertype of all types in the hybris Commerce Suite.4.types和attributes的关系就像Java中的类和属性之间的关系，5.创建types:1,1.Configured Types = types that are defined in an  items.xml  file.              2,2.Runtime Types - with no definition in  items.xml ; they are only defined in runtime in hMC.但是官方不加以使用运行时type的，因为他会在你重启项目时丢失，并切也不会生成Java资源文件6.AtomicTypes是hybris最基本的类型，它就像Java中的number和string类型。7.CollectionType 可以包含多种数据类型，可以是item中的type也可以是其他的CollectionTypes8.创建某个item时可以直接调用构造器方法，也可以使用Composed Type的newInstance( ... ) 方法(必须传入初始化参数，否则会报异常)Map params = new HashMap();params.put( HelloWorldWizardCronJob.SCREENTEXT, getScreenText() );params.put( HelloWorldWizardCronJob.ACTIVATE, isActivate() );params.put( HelloWorldWizardCronJob.INTERVAL, getInterval() ); ComposedType item = getSession().getTypeManager().getComposedType( 'mySampleItem' );      return (MySampleItem) item.newInstance( ctx, params（初始化参数） );9.contractData  --- contract --- PcacontractData:是和agreement一样的从不同的厂家获取到的b2b的合同和协议信息，但是格式很乱，我们需要用job将它们整理成我们可以使用的数据，就是先从contractData到contract然后又有另外的job跑contract到Pca。Pca就是我们内部可以使用的规范的数据，页面上判断是否可以展示此款产品，还有displayTo等都是用这个数据库的信息，他存储了商家的unit和产品的id 还有商品的startTime和endTime,";
	/**
	 * MD5加密
	 * @param plainText
	 * @return
	 */
	public static String md5(String plainText) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			//result = buf.toString(); //md5 32bit
			// result = buf.toString().substring(8, 24))); //md5 16bit
			result = buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

    /**
     * 标准32位Md5加密
     * @param plainText
     * @return
     */
    public static String encryption(String plainText) {
        if(null == plainText || "".equals(plainText)){
            return "";
        }
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes("utf-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        return re_md5;
    }
    
    public static String md5Salt(String plainText) {
        String mr = encryption(plainText);
        int i = Integer.parseInt(mr.substring(0, 1), 16);
        int j = Integer.parseInt(mr.substring(31, 32), 16);
		String mf = mr.substring(0, i) + encodingAddtion[j] + mr.substring(i) +str1;
        return encryption(mf);
    }
}
