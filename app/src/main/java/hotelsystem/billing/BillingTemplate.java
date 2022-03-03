package hotelsystem.billing;

abstract class BillingTemplate {
   abstract double BillCalc();

   abstract int Round(double num);

   abstract String Bill();

   abstract String DoubleToString(double num);

   abstract String IntToString(int num);

}
