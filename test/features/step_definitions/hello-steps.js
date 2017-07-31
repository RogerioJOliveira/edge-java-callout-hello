/* jslint node: true */
'use strict';

var apickli = require('apickli');

module.exports = function() {

    // cleanup before every scenario
    this.Before(function(scenario, callback) {
    	// TODO Update your org and env
        this.apickli = new apickli.Apickli('https', 'org-env.apigee.net');
        this.apickli.storeValueInScenarioScope("NAME_SHIFU", "Shifu");
        callback();
    });

	this.Given(/^I set form data to$/, function(formParameters, callback) {
		this.apickli.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		var content = formParameters.hashes().map(function(d) {
			return d.name + "=" + d.value;
		}).join("&");
		this.apickli.setRequestBody(content);
		callback();
	});

	this.Given(/^I set request to JSON data$/, function(properties, callback) {
		this.apickli.addRequestHeader("Content-Type", "application/json");
		this.apickli.setRequestBody(asJSON(properties));
		callback();
	});

	function asJSON(cells) {
		var obj = cells.hashes().reduce(function(obj, aCell) {
			obj[aCell.name] = aCell.value;
			return obj
		}, {});
		return JSON.stringify(obj);
	}

};