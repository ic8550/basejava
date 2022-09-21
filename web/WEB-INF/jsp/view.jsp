<%@ page import="club.swdev.webapp.model.ListSection" %>
<%@ page import="club.swdev.webapp.model.OrganizationSection" %>
<%@ page import="club.swdev.webapp.model.TextSection" %>
<%@ page import="club.swdev.webapp.util.UtilHtml" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/theme/${theme}.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/view-resume-styles.css">
    <jsp:useBean id="resume" type="club.swdev.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
  </head>
  <body>
    <jsp:include page="fragments/header.jsp"/>

    <div class="scrollable-panel">
      <div class="form-wrapper">
        <div class="full-name">${resume.fullName}
          <a class="no-underline-anchor" href="resumes?uuid=${resume.uuid}&action=edit&theme=${theme}">
            <img src="img/${theme}/edit.svg" alt="">
          </a>
        </div>
        <div class="contacts">
          <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<club.swdev.webapp.model.ContactType, java.lang.String>"/>
            <div>
              <%=contactEntry.getKey().toHtml(contactEntry.getValue())%>
            </div>
          </c:forEach>
        </div>

        <div class="spacer"></div>

        <c:forEach var="sectionEntry" items="${resume.sections}">
          <jsp:useBean id="sectionEntry"
                       type="java.util.Map.Entry<club.swdev.webapp.model.SectionType, club.swdev.webapp.model.AbstractSection>"/>
          <c:set var="sectionType" value="${sectionEntry.key}"/>
          <c:set var="section" value="${sectionEntry.value}"/>
          <jsp:useBean id="section" type="club.swdev.webapp.model.AbstractSection"/>
          <div class="section">${sectionType.translation}</div>
          <c:choose>
            <c:when test="${sectionType=='OBJECTIVE'}">
              <div
                  class="activity"><%=((TextSection) section).getText()%>
              </div>
            </c:when>
            <c:when test="${sectionType=='PERSONAL'}">
              <div
                  class="personal-data"><%=((TextSection) section).getText()%>
              </div>
            </c:when>
            <c:when test="${sectionType=='QUALIFICATIONS' || sectionType=='ACHIEVEMENTS'}">
              <ul class="list">
                <c:forEach var="item" items="<%=((ListSection) section).getContent()%>">
                  <li>${item}</li>
                </c:forEach>
              </ul>
            </c:when>
            <c:when test="${sectionType=='EXPERIENCE' || sectionType=='EDUCATION'}">
              <c:forEach var="organization" items="<%=((OrganizationSection) section).getOrganizations()%>">
                <div class="section-wrapper">
                  <c:choose>
                    <c:when test="${empty organization.homePage.url}">
                      <div class="organization-name">${organization.homePage.name}</div>
                    </c:when>
                    <c:otherwise>
                      <div class="organization-name">
                        <a class="contact-link" href="${organization.homePage.url}">${organization.homePage.name}</a>
                      </div>
                    </c:otherwise>
                  </c:choose>
                  <c:forEach var="activity" items="${organization.activities}">
                    <jsp:useBean id="activity" type="club.swdev.webapp.model.Organization.Activity"/>
                    <div class="activity-duration">
                      <div
                          class="duration"><%=UtilHtml.formatDates(activity)%>
                      </div>
                      <div class="activity">${activity.title}</div>
                    </div>
                    <c:choose>
                      <c:when test="${empty activity.description}">
                      </c:when>
                      <c:otherwise>
                        <div class="description">${activity.description}</div>
                      </c:otherwise>
                    </c:choose>
                  </c:forEach>
                </div>
              </c:forEach>
            </c:when>
          </c:choose>
        </c:forEach>

        <div class="footer-spacer"></div>
      </div>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
  </body>
</html>

