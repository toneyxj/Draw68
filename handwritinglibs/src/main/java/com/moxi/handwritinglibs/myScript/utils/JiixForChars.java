package com.moxi.handwritinglibs.myScript.utils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 创建时间：19-5-10
 * 创 建 人：黄文泰 Wade
 * 功    能：
 **/
public class JiixForChars {

    /**
     * type : Text
     * bounding-box : {"x":6.3797812,"y":3.4327211,"width":8.1600246,"height":3.4960938}
     * label : 一
     * chars : [{"label":"一","grid":[{"x":5.9725804,"y":-14.632766},{"x":15.483734,"y":-14.632766},{"x":15.483734,"y":24.470097},{"x":5.9725804,"y":24.470097}],"bounding-box":{"x":6.3797812,"y":3.4327211,"width":8.1600246,"height":3.4960938},"items":[{"timestamp":"2019-05-10 02:51:50.681000","X":[7.8797812,7.944973,8.0753565,8.3633251,8.4331169,8.6286917,8.9220552,8.9361849,9.2472191,9.5861235,9.6057739,10.093918,10.517664,10.598403,10.810232,11.005374,11.005808,11.233979,11.559143,11.64427,11.787314,12.048081,12.269384,12.276253,12.536225,12.699204,12.716083,12.829588,12.959972,13.000676,13.025164,13.039805,13.025164],"Y":[5.4288149,5.4288149,5.4288149,5.3909616,5.3817873,5.3338375,5.2868099,5.2845454,5.2397828,5.1282215,5.1217527,5.1217527,5.027698,5.0097775,5.027698,5.027698,5.027698,5.027698,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211],"F":[0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625],"T":[0,38,46,53,55,61,70,70,78,87,87,94,102,103,109,120,120,125,133,137,142,150,157,157,165,174,174,181,189,192,205,209,262],"type":"stroke","id":"0000030001004100ff00"}]}]
     * version : 2
     * id : MainBlock
     */

    private String type;
    @SerializedName("bounding-box")
    private BoundingboxBean boundingbox;
    private String label;
    private String version;
    private String id;
    private List<CharsBean> chars;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BoundingboxBean getBoundingbox() {
        return boundingbox;
    }

    public void setBoundingbox(BoundingboxBean boundingbox) {
        this.boundingbox = boundingbox;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CharsBean> getChars() {
        return chars;
    }

    public void setChars(List<CharsBean> chars) {
        this.chars = chars;
    }

    public static class BoundingboxBean {
        /**
         * x : 6.3797812
         * y : 3.4327211
         * width : 8.1600246
         * height : 3.4960938
         */

        private float x;
        private float y;
        private float width;
        private float height;

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public float getWidth() {
            return width;
        }

        public void setWidth(float width) {
            this.width = width;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }
    }

    public static class CharsBean {
        /**
         * label : 一
         * grid : [{"x":5.9725804,"y":-14.632766},{"x":15.483734,"y":-14.632766},{"x":15.483734,"y":24.470097},{"x":5.9725804,"y":24.470097}]
         * bounding-box : {"x":6.3797812,"y":3.4327211,"width":8.1600246,"height":3.4960938}
         * items : [{"timestamp":"2019-05-10 02:51:50.681000","X":[7.8797812,7.944973,8.0753565,8.3633251,8.4331169,8.6286917,8.9220552,8.9361849,9.2472191,9.5861235,9.6057739,10.093918,10.517664,10.598403,10.810232,11.005374,11.005808,11.233979,11.559143,11.64427,11.787314,12.048081,12.269384,12.276253,12.536225,12.699204,12.716083,12.829588,12.959972,13.000676,13.025164,13.039805,13.025164],"Y":[5.4288149,5.4288149,5.4288149,5.3909616,5.3817873,5.3338375,5.2868099,5.2845454,5.2397828,5.1282215,5.1217527,5.1217527,5.027698,5.0097775,5.027698,5.027698,5.027698,5.027698,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211],"F":[0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625],"T":[0,38,46,53,55,61,70,70,78,87,87,94,102,103,109,120,120,125,133,137,142,150,157,157,165,174,174,181,189,192,205,209,262],"type":"stroke","id":"0000030001004100ff00"}]
         */

        private String label;
        @SerializedName("bounding-box")
        private BoundingboxBeanX boundingbox;
        private List<GridBean> grid;
        private List<ItemsBean> items;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public BoundingboxBeanX getBoundingbox() {
            return boundingbox;
        }

        public void setBoundingbox(BoundingboxBeanX boundingbox) {
            this.boundingbox = boundingbox;
        }

        public List<GridBean> getGrid() {
            return grid;
        }

        public void setGrid(List<GridBean> grid) {
            this.grid = grid;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class BoundingboxBeanX {
            /**
             * x : 6.3797812
             * y : 3.4327211
             * width : 8.1600246
             * height : 3.4960938
             */

            private float x;
            private float y;
            private float width;
            private float height;

            public float getX() {
                return x;
            }

            public void setX(float x) {
                this.x = x;
            }

            public float getY() {
                return y;
            }

            public void setY(float y) {
                this.y = y;
            }

            public float getWidth() {
                return width;
            }

            public void setWidth(float width) {
                this.width = width;
            }

            public float getHeight() {
                return height;
            }

            public void setHeight(float height) {
                this.height = height;
            }
        }

        public static class GridBean {
            /**
             * x : 5.9725804
             * y : -14.632766
             */

            private float x;
            private float y;

            public float getX() {
                return x;
            }

            public void setX(float x) {
                this.x = x;
            }

            public float getY() {
                return y;
            }

            public void setY(float y) {
                this.y = y;
            }
        }

        public static class ItemsBean {
            /**
             * timestamp : 2019-05-10 02:51:50.681000
             * X : [7.8797812,7.944973,8.0753565,8.3633251,8.4331169,8.6286917,8.9220552,8.9361849,9.2472191,9.5861235,9.6057739,10.093918,10.517664,10.598403,10.810232,11.005374,11.005808,11.233979,11.559143,11.64427,11.787314,12.048081,12.269384,12.276253,12.536225,12.699204,12.716083,12.829588,12.959972,13.000676,13.025164,13.039805,13.025164]
             * Y : [5.4288149,5.4288149,5.4288149,5.3909616,5.3817873,5.3338375,5.2868099,5.2845454,5.2397828,5.1282215,5.1217527,5.1217527,5.027698,5.0097775,5.027698,5.027698,5.027698,5.027698,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211,4.9327211]
             * F : [0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625,0.50390625]
             * T : [0,38,46,53,55,61,70,70,78,87,87,94,102,103,109,120,120,125,133,137,142,150,157,157,165,174,174,181,189,192,205,209,262]
             * type : stroke
             * id : 0000030001004100ff00
             */

            private String timestamp;
            private String type;
            private String id;
            private List<Float> X;
            private List<Float> Y;
            private List<Float> F;
            private List<Long> T;

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public List<Float> getX() {
                return X;
            }

            public void setX(List<Float> X) {
                this.X = X;
            }

            public List<Float> getY() {
                return Y;
            }

            public void setY(List<Float> Y) {
                this.Y = Y;
            }

            public List<Float> getF() {
                return F;
            }

            public void setF(List<Float> F) {
                this.F = F;
            }

            public List<Long> getT() {
                return T;
            }

            public void setT(List<Long> T) {
                this.T = T;
            }
        }
    }
}
