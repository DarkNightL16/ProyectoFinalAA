package org.example.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class TiempoEjecucion {
        protected static final List<String> categorias = new ArrayList<>();
        protected static final List<Long> tiemposEjecucion = new ArrayList<>();
    public static void calcularTiempos(int i) {

        double[][] a;
        double[][] b;
        try {
            a = GeneradorMatrices.leerMatriz("./algoritmos/src/main/java/org/example/file_matrices/matriz_" + i + "_" + "num" + 1 + ".txt");
            b = GeneradorMatrices.leerMatriz("./algoritmos/src/main/java/org/example/file_matrices/matriz_" + i + "_" + "num" + 2 + ".txt");
            double[][] resultado = new double[a.length][b[0].length];
            int n = a.length;
            int p = b.length;
            int m = b[0].length;

            calcularNaivOnArray(a, b);
            calcularNaivLoopUnrollingTwo(a, b);
            calcularNaivLoopUnrollingFour(a, b);
            calcularWinogradOriginal(a, b, resultado, n, p, m);
            calcularWinogradScaled(a, b, resultado, n, p, m);
            calcularStrassenNaiv(a, b); //Realentiza demasiado el programa
            calcularStrassenWinograd(a, b, resultado, n, p, m);
            calcularIII3SequentialBlock(a, b, i, i/4);
            calcularIII4ParallelBlock(a, b);
            calcularIII5EnhanchedParallelBlock(a, b);
            calcularIV3SequentialBlock(a, b, i, i/4);
            calcularIV4ParallelBlock(a, b);
            calcularIV5EnhanchedParallelBlock(a, b);
            calcularV3SequentialBlock(a,b,i,i/4);
            calcularV4ParallelBlock(a,b);

            ChartGenerator.generateBarChart(
                categorias,
                tiemposEjecucion,
                "Tiempos de ejecución de los algoritmos con matrices de tamaño " + i,
                "Algoritmo",
                "Tiempo de ejecucion (ms)",
                i
        );

        categorias.clear();
        tiemposEjecucion.clear();

        JOptionPane.showMessageDialog(null, "Tiempos de ejecución de los algoritmos con matrices de tamaño " + i + " generados correctamente");

        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }

    }
    private static void calcularV4ParallelBlock(double[][] a, double[][] b) {
        long start = System.currentTimeMillis();
        V4_ParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("V4.ParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución V4.ParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("V.4ParallelBlock ", a.length, executionTime);
    }
    private static void calcularV3SequentialBlock(double[][] a, double[][] b, int i, int j) {
        long start = System.currentTimeMillis();
        V3_SequentialBlock.blockMatrixMultiply(a, b, i, j);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("V3.SequentialBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución V3.SequentialBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("V.3SequentialBlock ", a.length, executionTime);
    }
    private static void calcularIV5EnhanchedParallelBlock(double[][] a, double[][] b) {
        long start = System.currentTimeMillis();
        IV5_EnhancedParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("IV5.EnhanchedParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución IV5.EnhanchedParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("IV.5EnhanchedParallelBlock ", a.length, executionTime);
    }
    private static void calcularIV4ParallelBlock(double[][] a, double[][] b) {
        long start = System.currentTimeMillis();
        IV4_ParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("IV4.ParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución IV4.ParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("IV.4ParallelBlock ", a.length, executionTime);
    }
    private static void calcularIV3SequentialBlock(double[][] a, double[][] b, int i, int j) {
        long start = System.currentTimeMillis();
        IV3_SequentialBlock.blockMatrixMultiply(a, b, i, j);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("IV3.SequentialBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución IV3.SequentialBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("IV.3SequentialBlock ", a.length, executionTime);
    }
    private static void calcularIII5EnhanchedParallelBlock(double[][] a, double[][] b) {
        long start = System.currentTimeMillis();
        III5_EnhancedParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("III5.EnhanchedParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución III5.EnhanchedParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("III.5EnhanchedParallelBlock ", a.length, executionTime);
    }
    private static void calcularIII4ParallelBlock(double[][] a, double[][] b) {
        long start = System.currentTimeMillis();
        III4_ParallelBlock.matrixMultiplicationParallel(a, b, a.length, a.length/4);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("III4.ParallelBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución III4.ParallelBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("III.4ParallelBlock ", a.length, executionTime);
    }
    private static void calcularIII3SequentialBlock(double[][] a, double[][] b, int i, int j) {
        long start = System.currentTimeMillis();
        III3_SequentialBlock.blockMatrixMultiply(a, b, i, j);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("III3.SequentialBlock");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución III3.SequentialBlock: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("III.3SequentialBlock ", a.length, executionTime);
    }
    private static void calcularStrassenWinograd(double[][] a, double[][] b, double[][] resultado, int n, int p,
            int m) {
        long start = System.currentTimeMillis();
        StrassenWinograd.multiply(a, b, resultado, n, p, m);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("StrassenWinograd");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución StrassenWinograd: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("StrassenWinograd ", a.length, executionTime);

    }
    private static void calcularStrassenNaiv(double[][] a, double[][] b) {
        long start = System.currentTimeMillis();
        StrassenNaiv.multiply(a, b);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("StrassenNaiv");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución StrassenNaiv: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("StrassenNaiv ", a.length, executionTime);
    }
    private static void calcularWinogradScaled(double[][] a, double[][] b, double[][] resultado, int n, int p, int m) {
        long start = System.currentTimeMillis();
        WinogradScaled.multiply(a, b, resultado, n, p, m);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("WinogradScaled");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución WinogradScaled: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("WinogradScaled ", a.length, executionTime);
    }
    private static void calcularWinogradOriginal(double[][] a, double[][] b, double[][] resultado, int n, int p,
            int m) {
        long start = System.currentTimeMillis();
        WinogradOriginal.multiply(a, b, resultado, n, p, m);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("WinogradOriginal");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución WinogradOriginal: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("WinogradOriginal ", a.length, executionTime);
        
    }
    private static void calcularNaivLoopUnrollingFour(double[][] a, double[][] b) {
        long start = System.currentTimeMillis();
        NaivLoopUnrollingFour.multiply(a, b);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("NaivLoopUnrollingFour");
        tiemposEjecucion.add(executionTime);    
        System.out.println("Tiempo de ejecución NaivLoopUnrollingFour: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("NaivLoopUnrollingFour ", a.length, executionTime);
    }
    private static void calcularNaivLoopUnrollingTwo(double[][] a, double[][] b) {
        long start = System.currentTimeMillis();
        NaivLoopUnrollingTwo.multiply(a, b);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("NaivLoopUnrollingTwo");
        tiemposEjecucion.add(executionTime);    
        System.out.println("Tiempo de ejecución NaivLoopUnrollingTwo: " + executionTime + " milisegundos");
        PerformanceLogger.logPerformance("NaivLoopUnrollingTwo ", a.length, executionTime);
    }
    private static void calcularNaivOnArray(double[][] a, double[][] b) {
        long start = System.currentTimeMillis();
        NaivOnArray.multiply(a, b);
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        categorias.add("NaivOnArray");
        tiemposEjecucion.add(executionTime);
        System.out.println("Tiempo de ejecución NaivOnArray: " + executionTime + " milisegundos");    
        PerformanceLogger.logPerformance("NaivOnArray ", a.length, executionTime);
    }

}
