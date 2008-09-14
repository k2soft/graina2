import org.springframework.jmx.support.MBeanServerFactoryBean
import org.springframework.jmx.export.MBeanExporter
import org.hibernate.jmx.StatisticsService


// From http://docs.codehaus.org/display/GRAILS/MBean+export+the+Groovy+way

beans = {
    //first bean definition
    hibernateStats(StatisticsService) {
        statisticsEnabled = true
        sessionFactory = ref("sessionFactory")
    }


    mbeanServer(MBeanServerFactoryBean) {
        locateExistingServerIfPossible = true
    }

    //third bean definition
    exporter(MBeanExporter) {
        server = mbeanServer
        beans = ["org.hibernate:name=statistics": hibernateStats]
    }

}