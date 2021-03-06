/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mylhyl.zxing.scanner.encode;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.result.ParsedResultType;

/**
 * This class encodes data from an Intent into a QR code, and then displays it
 * full screen so that another person can scan it with their device.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class QREncode {
    private QRCodeEncoder mQRCodeEncoder;

    private QREncode() {
    }

    private QREncode(QRCodeEncoder codeEncoder) {
        this.mQRCodeEncoder = codeEncoder;
    }

    /**
     * @param codeEncoder {@linkplain Builder#buildDeprecated()} () QREncode.Builder()
     *                    .buildDeprecated()}
     * @return
     */
    @Deprecated
    public static Bitmap encodeQR(QRCodeEncoder codeEncoder) {
        try {
            return codeEncoder.encodeAsBitmap();
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * {@linkplain Builder#build()} () QREncode.Builder().build()}
     *
     * @return
     */
    public Bitmap encodeAsBitmap() {
        try {
            return mQRCodeEncoder.encodeAsBitmap();
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class Builder {

        BarcodeFormat barcodeFormat;
        private Context context;
        private ParsedResultType parsedResultType = ParsedResultType.TEXT;
        private Bundle bundle;
        private String contents;//?????????
        private String encodeContents;//????????????
        private int color;//??????
        private int[] colors;
        private Uri addressBookUri;
        private boolean useVCard = true;
        private int size;
        private Bitmap logoBitmap;
        private int logoSize;
        private float logoBorder;
        private int logoBorderColor = -1;
        private QRLogoBorderType logoBorderType = QRLogoBorderType.ROUNDED;
        private float logoBorderRadius = 30f;
        private Bitmap qrBackground;
        private int qrBackgroundColor;
        private int margin = 4;

        public Builder(Context context) {
            this.context = context;
        }

        BarcodeFormat getBarcodeFormat() {
            return barcodeFormat;
        }

        Builder setBarcodeFormat(BarcodeFormat barcodeFormat) {
            this.barcodeFormat = barcodeFormat;
            return this;
        }

        ParsedResultType getParsedResultType() {
            return parsedResultType;
        }

        /**
         * ?????????????????????
         *
         * @param parsedResultType {@linkplain ParsedResultType ParsedResultType}
         * @return
         */
        public Builder setParsedResultType(ParsedResultType parsedResultType) {
            this.parsedResultType = parsedResultType;
            return this;
        }

        Uri getAddressBookUri() {
            return addressBookUri;
        }

        /**
         * ???????????????Uri
         *
         * @param addressBookUri
         */
        public Builder setAddressBookUri(Uri addressBookUri) {
            this.addressBookUri = addressBookUri;
            return this;
        }

        Bundle getBundle() {
            return bundle;
        }

        /**
         * ?????????????????? ParsedResultType ??? ADDRESSBOOK ???GEO ??????
         *
         * @param bundle
         * @return
         */
        public Builder setBundle(Bundle bundle) {
            this.bundle = bundle;
            return this;
        }

        String getContents() {
            return contents;
        }

        /**
         * ???????????????
         *
         * @param contents tel???email??????????????????
         * @return
         */
        public Builder setContents(String contents) {
            this.contents = contents;
            return this;
        }

        String getEncodeContents() {
            return encodeContents;
        }

        Builder setEncodeContents(String encodeContents) {
            this.encodeContents = encodeContents;
            return this;
        }

        int getColor() {
            return color;
        }

        /**
         * ?????????????????????
         *
         * @param color
         * @return
         */
        public Builder setColor(int color) {
            this.color = color;
            return this;
        }

        int[] getColors() {
            return colors;
        }

        /**
         * ?????????????????????
         *
         * @param leftTop     ??????
         * @param leftBottom  ??????
         * @param rightBottom ??????
         * @param rightTop    ??????
         * @return
         */
        public Builder setColors(int leftTop, int leftBottom, int rightBottom, int rightTop) {
            this.colors = null;
            this.colors = new int[4];
            this.colors[0] = leftTop;
            this.colors[1] = leftBottom;
            this.colors[2] = rightBottom;
            this.colors[3] = rightTop;
            return this;
        }

        boolean isUseVCard() {
            return useVCard;
        }

        /**
         * ??????vCard???????????????true
         *
         * @param useVCard
         * @return
         */
        public Builder setUseVCard(boolean useVCard) {
            this.useVCard = useVCard;
            return this;
        }

        int getSize() {
            return size;
        }

        /**
         * ???????????????
         *
         * @param size
         * @return
         */
        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        /**
         * ??????????????????logo
         *
         * @param logoBitmap
         * @param logoSize   ???0??????????????????????????????1/5
         * @return
         */
        public Builder setLogoBitmap(Bitmap logoBitmap, int logoSize) {
            this.logoBitmap = logoBitmap;
            this.logoSize = logoSize;
            return this;
        }

        float getLogoBorder() {
            return logoBorder;
        }

        /**
         * ?????????logo??????
         *
         * @param width ??????
         * @return
         * @since 2.1.8
         */
        public Builder setLogoBorder(float width) {
            this.logoBorder = width;
            return this;
        }

        int getLogoBorderColor() {
            return logoBorderColor;
        }

        /**
         * ?????????logo????????????
         *
         * @param color RGB?????????
         * @return
         * @since 2.1.8
         */
        public Builder setLogoBorderColor(int color) {
            this.logoBorderColor = color;
            return this;
        }

        QRLogoBorderType getLogoBorderType() {
            return logoBorderType;
        }

        /**
         * ?????????logo????????????
         *
         * @param type ??????????????????????????????
         * @return
         * @since 2.1.8
         */
        public Builder setLogoBorderType(QRLogoBorderType type) {
            this.logoBorderType = type;
            return this;
        }

        float getLogoBorderRadius() {
            return logoBorderRadius;
        }

        /**
         * ?????????logo????????????????????????
         *
         * @param radius ?????????????????????30
         * @return
         * @since 2.1.8
         */
        public Builder setLogoBorderRadius(float radius) {
            this.logoBorderRadius = radius;
            return this;
        }

        /**
         * @return
         * @deprecated {@link #build()}
         */
        @Deprecated
        public QRCodeEncoder buildDeprecated() {
            checkParams();
            QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(this, context.getApplicationContext());
            return qrCodeEncoder;
        }

        public QREncode build() {
            checkParams();
            QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(this, context.getApplicationContext());
            return new QREncode(qrCodeEncoder);
        }

        int getQrBackgroundColor() {
            return qrBackgroundColor;
        }

        int getMargin() {
            return margin;
        }

        /**
         * ?????????????????????
         *
         * @param margin ????????????0-4
         * @return
         */
        public Builder setMargin(int margin) {
            this.margin = margin;
            return this;
        }

        int getLogoSize() {
            return logoSize;
        }

        Bitmap getQrBackground() {
            return qrBackground;
        }

        /**
         * ?????????????????????
         *
         * @param background
         * @return
         */
        public Builder setQrBackground(Bitmap background) {
            this.qrBackground = background;
            return this;
        }

        /**
         * ?????????????????????
         *
         * @param background rgb?????????
         * @return
         */
        public Builder setQrBackground(int background) {
            this.qrBackgroundColor = background;
            return this;
        }

        Bitmap getLogoBitmap() {
            return logoBitmap;
        }

        /**
         * ??????????????????logo
         *
         * @param logoBitmap
         * @return
         */
        public Builder setLogoBitmap(Bitmap logoBitmap) {
            this.logoBitmap = logoBitmap;
            return this;
        }

        private void checkParams() {
            if (context == null)
                throw new IllegalArgumentException("context no found...");
            if (parsedResultType == null) {
                throw new IllegalArgumentException("parsedResultType no found...");
            }
            if (parsedResultType != ParsedResultType.ADDRESSBOOK && parsedResultType != ParsedResultType.GEO
                    && contents == null) {
                throw new IllegalArgumentException("parsedResultType not" +
                        " ParsedResultType.ADDRESSBOOK and ParsedResultType.GEO, contents no " +
                        "found...");
            }
            if ((parsedResultType == ParsedResultType.ADDRESSBOOK || parsedResultType == ParsedResultType.GEO)
                    && bundle == null && addressBookUri == null) {
                throw new IllegalArgumentException("parsedResultType yes" +
                        " ParsedResultType.ADDRESSBOOK or ParsedResultType.GEO, bundle and " +
                        "addressBookUri no found...");
            }
        }
    }
}
