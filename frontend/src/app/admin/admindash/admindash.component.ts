import { Component } from '@angular/core';
import {CanvasJSAngularChartsModule} from "@canvasjs/angular-charts";

@Component({
  selector: 'app-admindash',
  standalone: true,
  imports: [
    CanvasJSAngularChartsModule
  ],
  templateUrl: './admindash.component.html',
  styleUrl: './admindash.component.scss'
})
export class AdminDashComponent {
  private dataPointsLineChart: { x: Date, y: number }[] = [];
  private dataPointsPieChart: { y: number, name: string }[] = [];

  chartOptionsLineChart = {
    animationEnabled: true,
    axisY: {
      title: "Omzet",
      valueFormatString: "#0,,.",
      suffix: ""
    },
    data: [{
      type: "splineArea",
      color: "rgba(54,158,173,.7)",
      xValueFormatString: "YYYY",
      dataPoints: this.dataPointsLineChart
    }]
  }
  chartOptionsPieChart = {
    animationEnabled: true,
    data: [{
      type: "pie",
      startAngle: 45,
      indexLabel: "{name}: {y}",
      indexLabelPlacement: "inside",
      yValueFormatString: "#,###.##'%'",
      dataPoints: this.dataPointsPieChart
    }]
  }

  ngOnInit() {
    const startYear = 2010;
    const currentYear = new Date().getFullYear();

    for (let year = startYear; year <= currentYear; year++) {
      this.dataPointsLineChart.push({
        x: new Date(year, 0),
        y: 150 + Math.floor(Math.random() * 10)
      });
    }

    this.dataPointsPieChart.push({ y: 450, name: "Later betalen" });
    this.dataPointsPieChart.push({ y: 120, name: "Termijn 1" });
    this.dataPointsPieChart.push({ y: 300, name: "Termijn 2" });
    this.dataPointsPieChart.push({ y: 800, name: "Termijn 3" });
  }

}
