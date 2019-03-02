import {Component, OnChanges, OnInit } from '@angular/core';
import { Chart } from 'chart.js';

declare function drawBarGraph() : any;

@Component ({
    selector: 'chart',
    templateUrl: './app.chart.html',
    styleUrls: ['./app.chart.css'],
})

export class ChartComponent implements OnInit{
   
    ngOnInit(){
       this.drawEmployeeGraph();
       this.drawVendorGraph();
       this.drawMonthlyGraph();
       this.drawStudentsPie();
    }
    
        
    drawEmployeeGraph(){

        var ctx = document.getElementById("myEmployeeChart");
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ["TX", "CO", "FL", "NY", "OK", "VA","GA", "LA", "OH", "KN", "IL", "MI"],
                datasets: [{
                    label: 'Current Employee Distribution',
                    data: [12, 19, 3, 5, 2, 3, 12, 19, 3, 5, 2, 3],
                    backgroundColor: [
                        "rgba(255,99,132,0.8)",
                        "rgba(255,99,132,0.8)",
                        "rgba(255,99,132,0.8)",
                        "rgba(255,99,132,0.8)",
                        "rgba(255,99,132,0.8)",
                        "rgba(255,99,132,0.8)",
                        "rgba(255,99,132,0.8)",
                        "rgba(255,99,132,0.8)",
                        "rgba(255,99,132,0.8)",
                        "rgba(255,99,132,0.8)",
                        "rgba(255,99,132,0.8)",
                        "rgba(255,99,132,0.8)",                       
                    ],
                    borderColor: [
                        "rgba(255,99,132,1)",
                        "rgba(255,99,132,1)",
                        "rgba(255,99,132,1)",
                        "rgba(255,99,132,1)",
                        "rgba(255,99,132,1)",
                        "rgba(255,99,132,1)",
                        "rgba(255,99,132,1)",
                        "rgba(255,99,132,1)",
                        "rgba(255,99,132,1)",
                        "rgba(255,99,132,1)",                        "rgba(255,99,132,1)",
                        "rgba(255,99,132,1)"
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                maintainAspectRatio: false,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                }
            }
        });
    }

    
    drawVendorGraph(){

        var ctx = document.getElementById("myVendorChart");
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ["Wipro", "Idc", "IBM", "Apple", "Zealcon"],
                datasets: [{
                    label: 'Employee by Vendors',
                    data: [12, 19, 3, 5, 2, 3, 12, 19, 3, 5, 2, 3],
                    backgroundColor: [
                        "rgba(54, 162, 235,0.8)",
                        "rgba(54, 162, 235,0.8)",
                        "rgba(54, 162, 235,0.8)",
                        "rgba(54, 162, 235,0.8)",
                        "rgba(54, 162, 235,0.8)"
                 
                    ],
                    borderColor: [

                    ],
                    borderWidth: 1
                }]
            },            
            options: {
                maintainAspectRatio: false,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                }
            }
        });
    }

    drawMonthlyGraph(){

        var ctx = document.getElementById("myMonthlyChart");
        var myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ["Jan", "Feb", "Mar", "Apr", "May","Jun", "Jul", "Aug", "Sep", "Oct","Nov", "Dec"],
                datasets: [{
                    label: 'Monthly stats',
                    data: [12, 19, 3, 5, 2, 3, 12, 19, 3, 5, 2, 3],
                    backgroundColor: [
                        'rgba(34,139,34, 0.8)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,0)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                maintainAspectRatio: false,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                }
            }
        });
    }

    drawStudentsPie(){

        var ctx = document.getElementById("myStudentsPie");
        var myChart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: ["On Site", "Training", "Marketting"],
                datasets: [{
                    label: 'Monthly stats',
                    data: [45,18,37],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(255, 206, 86, 0.8)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                maintainAspectRatio: false,
            }
        });
    }
}
