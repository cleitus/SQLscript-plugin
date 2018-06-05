/**
 *     This file is part of the Squashtest platform.
 *     Copyright (C) 2011 - 2018 Henix
 *
 *     See the NOTICE file distributed with this work for additional
 *     information regarding copyright ownership.
 *
 *     This is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     this software is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with this software.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.squashtest.ta.plugin.db.resources

import org.squashtest.ta.plugin.db.resources.SQLIndexedParameters;

import spock.lang.Specification

class SQLIndexedParametersTest extends Specification {
	
	def parameters = new SQLIndexedParameters(["bob", "ted", "mike"] as List)
	
	def "should replace the parameters (equal number of params)"(){
		
		given :
			def sql = "select * from COMPANY where col1 = ? and col2 = ? and col3 = ?;"
		
		
		when :
			def res = parameters.setParams(sql)
		
		then :
			res == "select * from COMPANY where col1 = bob and col2 = ted and col3 = mike;"
		
	}
	
	def "should replace the parameters (excess params)"(){
		
		given :
			def sql = "select * from COMPANY where col1 = ? and col2 = ? ;"
		
		
		when :
			def res = parameters.setParams(sql)
		
		then :
			res == "select * from COMPANY where col1 = bob and col2 = ted ;"
		
	}
	
	def "should replace the parameters (insufficient params)"(){
		
		given :
			def sql = "select * from COMPANY where col1 = ? and col2 = ? and col3 = ? and col4 = ?;"
		
		
		when :
			def res = parameters.setParams(sql)
		
		then :
			res == "select * from COMPANY where col1 = bob and col2 = ted and col3 = mike and col4 = ?;"
		
	}
}
