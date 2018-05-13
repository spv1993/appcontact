<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:default-template>

  <jsp:attribute name="title">AppContact</jsp:attribute>

  <jsp:body>
	<div ng-controller="ContactsCtrl" ng-cloak layout-gt-sm="row" layout="column">
		<div flex>
			
			<md-toolbar layout="row" class="md-hue-3">
		      <div class="md-toolbar-tools">
		        <span>Normal</span>
		      </div>
		    </md-toolbar>
		    
		    <md-content>
      			<md-list flex>
      				<md-subheader class="md-no-sticky">3 line item (with hover)</md-subheader>
				    <md-list-item class="md-3-line" ng-repeat="contact in contacts" ng-click="null">
			          <img ng-src="{{item.face}}?{{$index}}" class="md-avatar" alt="{{item.who}}" />
			          <div class="md-list-item-text" layout="column">
			            <h3>{{ contact.firstName }} {{ contact.lastName }}</h3>
			            <h4>{{ contact.email }}</h4>
			            <p>{{ contact.phone }}</p>
			          </div>
			        </md-list-item>
		        </md-list>
	        </md-content>
		</div>
	</div>
  </jsp:body>

</page:default-template>