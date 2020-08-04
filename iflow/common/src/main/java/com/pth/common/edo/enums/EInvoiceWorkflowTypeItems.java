package com.pth.common.edo.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum EInvoiceWorkflowTypeItems implements IEnumNameValidator {

  INVOCIE_SENDER("invoice-sender"),

  INVOCIE_NUMBER("invoice-invoicenumber"),

  INVOCIE_DATE("invoice-invoicedate"),

  PARTNER_CODE("invoice-partnercode"),

  VENDOR_NUMBER("invoice-vendornumber"),

  VENDOR_NAME("invoice-vendorname"),

  IS_DIRECT_DEBIT_PERMISSION("invoice-isdirectdebitpermission"),

  INVOICE_TYPE("invoice-invoicetype"),

  DISCOUNT_ENTERDATE("invoice-discountenterdate"),

  DISCOUNT_DEADLINE("invoice-discountdeadline"),

  DISCOUNT_RATE("invoice-discountrate"),

  DISCOUNT_DATE("invoice-discountdate"),

  PAYMENT_AMOUNT("invoice-paymentamount");

  EInvoiceWorkflowTypeItems(final String itemIdentity) {

    this.itemIdentity = itemIdentity;

  }

  private final String itemIdentity;

  @Override
  public String getIdentity() {

    return this.itemIdentity;
  }

  public static List<String> toIdentityList() {

    return Arrays.asList(values()).stream().map(e -> e.getIdentity()).collect(Collectors.toList());
  }

}
