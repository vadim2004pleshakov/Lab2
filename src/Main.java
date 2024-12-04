import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args){
        //инициаизация и ввод параметров
        out.println("Введите количество строк:");
        int n = in.nextInt();
        out.println("Введите количество столбцов:");
        int m = in.nextInt();
        if((m<=0)||(n<=0)){//проверка на дурака
            out.println("Количество подразумевает натуральное число! НИЧЕГО НЕ БУДЕТ!");
        }
        if((m>0)&&(n>0)){
        //инициализация: массивы
        int[][]a=new int[n][m];//вводимый массив
            //служебные
        int[]b=new int[m*n];//одномерный аналог вводимого массива
        int[]sums=new int[m];//для подсчёта и хранения сумм по стобцам
        int[]unics=new int[n];//для подсчёта уникальных по стобцам
        int[]stolb=new int[m];//для хранения уникальных по стобцам
        //инициализация: служебные переменные
        int l = 0;//для одномерного аналога вводимого массива
        int neue=1;//для подсчёта факториалов (это по-немецки, по-англицки джава ругалась)
            //для перестановок
            int o = 0;
            int p = 0;
            int oo = 0;
            int pp = 0;
            //для подсчета уникальных
            int cnt = 0;
            int min = 9999;
            int max = -9999;
        out.println("Введите элементы массива, коих "+(m*n)+" штук:");
        for (int i=0; i<n; i++){//ввод массива
            for (int j=0; j<m; j++){
                a[i][j] = in.nextInt();
                b[l] = a[i][j];//вводится одновременно и одномерная копия
                l++;}}
        out.println("Введённый массив: ");
        for (int i=0; i<a.length; i++) {//вывод введенного массива
            for (int j=0; j<a[i].length; j++){
                out.print(a[i][j]+" ");}
            out.println();}
        //счет уникальных
        for (int i=0; i<m*n; i++){//ищем минимум и максимум массива
            if(b[i]<min){//используя его одномерную копию
                min=b[i];}
            if(b[i]>max){
                max=b[i];}}
        for (int i=min; i<=max; i++){//проверяем существование значений
            for (int j=0; j<m*n; j++){// от минимума до максимума
                if (b[j]==i){cnt++;break;}}}
        out.println("Количество уникальных элементов: "+cnt);
        //счет сумм в стобцах (для дальнейшей сортировки)
        for (int j=0; j<m; j++){
            for(int i=0; i<n; i++){
                sums[j]=sums[j]+a[i][j];}}
        //out.println();out.print("Суммы:");for(int q=0;q<m;q++){out.print(sums[q]+" ");}
        //счет уникальных по столбцам (для дальнейшей сортировки)
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                unics[j]=a[j][i];}
            min=9999;
            max=-9999;
            cnt=0;//аналогично счёту уникальных выше, отдельно расматриваем стобцы
            for (int k=0; k<n; k++){
                if(unics[k]<min){
                    min=unics[k];}
                if(unics[k]>max){
                    max=unics[k];}}
            for (int k=min; k<=max; k++){
                for (int j=0; j<n; j++){
                    if (unics[j]==k){cnt++;break;}}}
            stolb[i]=cnt;}
        //out.println();out.print("Уникальные:");for(int q=0;q<m;q++){out.print(stolb[q]+" ");}
        //перестановка столбцов согласно заданию
        for(int i=0;i<m;i++){
            for(int j=i+1;j<m;j++){
                if(sums[i]>sums[j]){//по сумме
                    o=sums[i];
                    p=sums[j];
                    sums[i]=p;
                    sums[j]=o;
                    for(int q=0;q<n;q++){
                        oo=a[q][i];
                        pp=a[q][j];
                        a[q][i]=pp;
                        a[q][j]=oo;
                        oo=0;
                        pp=0;}
                    o=0;
                    p=0;}
                if(sums[i]==sums[j]){//в случае одинаковой суммы по количеству уникальных
                    if(stolb[i]>stolb[j]){
                        o=stolb[i];
                        p=stolb[j];
                        stolb[i]=p;
                        stolb[j]=o;
                        for(int q=0;q<n;q++){
                            oo=a[q][i];
                            pp=a[q][j];
                            a[q][i]=pp;
                            a[q][j]=oo;
                            oo=0;
                            pp=0;}}}}}
        out.println("Отсортированный массив: ");
        for (int i=0; i<a.length; i++) {//вывод массива после перестановки
            for (int j=0; j<a[i].length; j++){
                out.print(a[i][j]+" ");}
            out.println();}
        //вывод по диагоналям согласно заданию
        if(m<n){out.println("Нету полноценных диагоналей");}
        if(m>=n){
        out.println("Диагонали: ");
        for(int j=0;j<m-n+1;j++){
            for (int i=0;i<n;i++) {
                out.print(a[i][i+j] + " ");
            }out.println();}}
        //замена на факториалы и вывод
        out.println("Факториал определён только для натуральных чисел, для");
        out.println("неположительных значений будет выведен 0");//забота об ОДЗ факториала
            for (int i=0; i<a.length; i++){
            for (int j=0; j<a[i].length; j++){
                if(a[i][j]<=0){a[i][j]=0;}//вывод нулей в случае элемента не из ОДЗ
                if(a[i][j]>0){
                for (int k=1; k<=a[i][j]; k++){
                    neue=neue*k;}
                    a[i][j]=neue;
                    neue=1;}}}
                    //если не выводить нули, то вылезут 1, но 1 это 1!
        out.println("С элементами заменёнными на их факториалы: ");
        for (int i=0; i<a.length; i++) {
            for (int j=0; j<a[i].length; j++){
                out.print(a[i][j]+" ");}
            out.println();}}}}