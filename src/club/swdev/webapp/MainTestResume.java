package club.swdev.webapp;

import club.swdev.webapp.model.Activity;
import club.swdev.webapp.model.ActivityInstance;
import club.swdev.webapp.model.Resume;
import club.swdev.webapp.model.TextSection;

import java.util.*;

public class MainTestResume {
    public static Resume resume = new Resume("Григорий Кислин");

    public static void main(String[] args) {
        // Fill Contacts
        Map<String, String> contacts = new LinkedHashMap<>();
        contacts.put("Тел.", "+7 (921) 855-0482");
        contacts.put("Skype", "grigory.kislin");
        contacts.put("Почта", "gkislin@yandex.ru");
        contacts.put("Профиль LinkedIn", "https://www.linkedin.com/in/gkislin");
        contacts.put("Профиль GitHub", "https://github.com/gkislin");
        contacts.put("Профиль Stackoverflow", "https://stackoverflow.com/users/548473");
        contacts.put("Домашняя страница", "http://gkislin.ru/");
        resume.setContacts(contacts);

        // Fill Objective
        resume.setObjectives(new String[]{
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"
        });

        // Fill Personal
        resume.setPersonal(new String[]{
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."
        });

        // Fill Achievements
        resume.setAchievements(new String[]{
                "Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет",
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."
        });

        // Fill Skills
        resume.setSkills(new String[]{
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy",
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).",
                "Python: Django.",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования",
                "Родной русский, английский \"upper intermediate\""
        });

        // Fill Employments
        Activity[] activities = {
                createActivity("Java Online Projects", "http://javaops.ru/",
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "10/2013", "по настоящее время",
                                        "Автор проекта",
                                        new String[]{
                                                "Создание, организация и проведение Java онлайн проектов и стажировок"
                                        }
                                )
                        }
                ),
                createActivity("Wrike", "https://www.wrike.com",
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "10/2014", "01/2016",
                                        "Старший разработчик (backend)",
                                        new String[]{
                                                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."
                                        }
                                )
                        }
                ),
                createActivity("RIT Center", null,
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "04/2012", "10/2014",
                                        "Java архитектор",
                                        new String[]{
                                                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"
                                        }
                                )
                        }
                ),
                createActivity("Java Online Projects", "http://javaops.ru/",
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "10/2013", "по настоящее время",
                                        "Автор проекта",
                                        new String[]{
                                                "Создание, организация и проведение Java онлайн проектов и стажировок"
                                        }
                                )
                        }
                ),
                createActivity("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "12/2010", "04/2012",
                                        "Ведущий программист",
                                        new String[]{
                                                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."
                                        }
                                )
                        }
                ),
                createActivity("Yota", "https://www.yota.ru/",
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "06/2008", "04/2010",
                                        "Ведущий специалист",
                                        new String[]{
                                                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS"
                                        }
                                )
                        }
                ),
                createActivity("Enkata", "http://enkata.com/",
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "03/2007", "06/2008",
                                        "Разработчик ПО",
                                        new String[]{
                                                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)"
                                        }
                                )
                        }
                ),
                createActivity("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "01/2005", "02/2007",
                                        "Разработчик ПО",
                                        new String[]{
                                                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)"
                                        }
                                )
                        }
                ),
                createActivity("Alcatel", "http://www.alcatel.ru/",
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "09/1997", "01/2005",
                                        "Инженер по аппаратному и программному тестированию",
                                        new String[]{
                                                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)"
                                        }
                                )
                        }
                )
        };
        resume.setEmployments(activities);

        // Fill Education
        activities = new Activity[]{
                createActivity("Coursera", "https://www.coursera.org/course/progfun",
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "03/2013", "05/2013",
                                        "Functional Programming Principles in Scala' by Martin Odersky",
                                        null
                                )
                        }
                ),
                createActivity("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "03/2011", "04/2011",
                                        "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML\"",
                                        null
                                )
                        }
                ),
                createActivity("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "01/2005", "04/2005",
                                        "3 месяца обучения мобильным IN сетям (Берлин)",
                                        null
                                )
                        }
                ),
                createActivity("Alcatel", "http://www.alcatel.ru/",
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "09/1997", "03/1998",
                                        "6 месяцев обучения цифровым телефонным сетям (Москва)",
                                        null
                                )
                        }
                ),
                createActivity("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/",
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "09/1993", "07/1996",
                                        "Ведущий специалист",
                                        null
                                ),
                                new ActivityInstance(
                                        "09/1987", "07/1993",
                                        "Ведущий специалист",
                                        null
                                )
                        }

                ),
                createActivity("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/",
                        new ActivityInstance[]{
                                new ActivityInstance(
                                        "09/1984", "06/1987",
                                        "Закончил с отличием",
                                        null
                                )
                        }
                ),
        };
        resume.setEducation(activities);

        printResume(resume);
    }

    static Activity createActivity(String orgName, String orgUrl, ActivityInstance[] activityInstances) {
        return new Activity(orgName, orgUrl, activityInstances);
    }

    public static void printFullName(String fullName) {
        System.out.println(fullName);
    }

    public static void printContacts(Map<String, String> contacts) {
        System.out.println("CONTACTS:");
        contacts.forEach((key, value) -> System.out.println("    " + key + ": " + value));
    }

    public static void printObjectives(ArrayList<String> objectives) {
        System.out.println("OBJECTIVES:");
        printList(objectives, "    ");
    }

    public static void printPersonal(ArrayList<String> personal) {
        System.out.println("PERSONAL DATA:");
        printList(personal, "    ");
    }

    public static void printAchievements(ArrayList<String> achievements) {
        System.out.println("ACHIEVEMENTS:");
        printList(achievements, "    ");
    }

    public static void printSkills(ArrayList<String> skills) {
        System.out.println("SKILLS:");
        printList(skills, "    ");
    }

    public static void printEmployments(ArrayList<Activity> employments) {
        System.out.println("PROFESSIONAL EXPERIENCE:");
        for (int i = 0; i < employments.size(); i++) {
            printActivity(employments.get(i), "    ");
        }
    }

    public static void printEducation(ArrayList<Activity> education) {
        System.out.println("EDUCATION AND TRAINING:");
        for (int i = 0; i < education.size(); i++) {
            printActivity(education.get(i), "    ");
        }
    }

    public static void printActivity(Activity activity, String indent) {
        System.out.print(indent);
        System.out.println(activity.getOrgName() + " (" + activity.getOrgUrl() + ")");
        printActivityInstances(activity.getActivityInstances(), indent + "    ");
    }

    public static void printActivityInstances(ArrayList<ActivityInstance> activityInstance, String indent) {
        for (int i = 0; i < activityInstance.size(); i++) {
            printActivityInstance(activityInstance.get(i), indent);
        }
    }

    public static void printActivityInstance(ActivityInstance activityInstance, String indent) {
        System.out.print(indent);
        System.out.println(activityInstance.getFormDate() + " - " + activityInstance.getToDate());
        printTextSection(activityInstance.getOccupation(), indent + "    ");
    }

    public static void printTextSection(TextSection textSection, String indent) {
        System.out.print(indent);
        System.out.println(textSection.getTitle());
        printList(textSection.getDescriptions(), indent + "    ");
    }

    public static void printList(List list, String indent) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(indent);
            System.out.println(list.get(i));
        }
    }

    public static void printResume(Resume resume) {
        printFullName(resume.getFullName());
        System.out.println("\n");
        printContacts(resume.getContacts());
        System.out.println("\n");
        printObjectives(resume.getObjectives());
        System.out.println("\n");
        printPersonal(resume.getPersonal());
        System.out.println("\n");
        printAchievements(resume.getAchievements());
        System.out.println("\n");
        printSkills(resume.getSkills());
        System.out.println("\n");
        printEmployments(resume.getEmployments());
        System.out.println("\n");
        printEducation(resume.getEducation());
    }
}

