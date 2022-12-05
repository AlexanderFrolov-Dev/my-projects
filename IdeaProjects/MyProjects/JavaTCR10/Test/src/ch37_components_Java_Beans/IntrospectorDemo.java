package ch37_components_Java_Beans;

// Продемонстрировать свойства и события.

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class IntrospectorDemo {
    public static void main(String args[]) {
        try {
            Class<?> c = Class.forName("ch37_components_Java_Beans.Colors");
            BeanInfo beanInfo = Introspector.getBeanInfo(c);

            System.out.println("Свойства:");
            PropertyDescriptor propertyDescriptor[] =
                    beanInfo.getPropertyDescriptors();
            for(int i = 0; i < propertyDescriptor.length; i++) {
                System.out.println("\t" + propertyDescriptor[i].getName());
            }

            System.out.println("События:");
            EventSetDescriptor eventSetDescriptor[] =
                    beanInfo.getEventSetDescriptors();
            for(int i = 0; i < eventSetDescriptor.length; i++) {
                System.out.println("\t" + eventSetDescriptor[i].getName());
            }
        }
        catch(Exception e) {
            System.out.println("Перехвачено событие. " + e);
        }
    }
}