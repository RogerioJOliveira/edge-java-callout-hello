'use strict';

module.exports = function(grunt) {
    grunt.initConfig({
        cucumberjs: {
            features: [
                // 'features/hello.feature'
            ],
            options: {
                // tags: '@current',
                format: 'pretty'
            }
        }
    });

    grunt.loadNpmTasks('grunt-cucumber');
    grunt.registerTask('default', ['cucumberjs']);
}