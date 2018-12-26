package com.dotstudioz.dotstudioPRO.corelibrary.models;

public class BillingAddress {

        private String billing_country;

        private String billing_zip;

        private String first_name;

        private String billing_city;

        private String last_name;

        private String billing_state;

        private String coupon;

        private String billing_address_2;

        private String billing_address;

        public String getBilling_country ()
        {
            return billing_country;
        }

        public void setBilling_country (String billing_country)
        {
            this.billing_country = billing_country;
        }

        public String getBilling_zip ()
        {
            return billing_zip;
        }

        public void setBilling_zip (String billing_zip)
        {
            this.billing_zip = billing_zip;
        }

        public String getFirst_name ()
        {
            return first_name;
        }

        public void setFirst_name (String first_name)
        {
            this.first_name = first_name;
        }

        public String getBilling_city ()
        {
            return billing_city;
        }

        public void setBilling_city (String billing_city)
        {
            this.billing_city = billing_city;
        }

        public String getLast_name ()
        {
            return last_name;
        }

        public void setLast_name (String last_name)
        {
            this.last_name = last_name;
        }

        public String getBilling_state ()
        {
            return billing_state;
        }

        public void setBilling_state (String billing_state)
        {
            this.billing_state = billing_state;
        }

        public String getCoupon ()
        {
            return coupon;
        }

        public void setCoupon (String coupon)
        {
            this.coupon = coupon;
        }

        public String getBilling_address_2 ()
        {
            return billing_address_2;
        }

        public void setBilling_address_2 (String billing_address_2)
        {
            this.billing_address_2 = billing_address_2;
        }

        public String getBilling_address ()
        {
            return billing_address;
        }

        public void setBilling_address (String billing_address)
        {
            this.billing_address = billing_address;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [billing_country = "+billing_country+", billing_zip = "+billing_zip+", first_name = "+first_name+", billing_city = "+billing_city+", last_name = "+last_name+", billing_state = "+billing_state+", coupon = "+coupon+", billing_address_2 = "+billing_address_2+", billing_address = "+billing_address+"]";
        }
    }

