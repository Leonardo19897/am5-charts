package it.shark.renderer;

import it.shark.axis.AxisType;

import java.util.Locale;

public class AxisRenderer {

    private final AxisType type;

    private Integer minGridDistance;

    private Integer rotation;
    private String centerX;
    private String centerY;
    private Integer paddingRight;

    public AxisRenderer(AxisType type) {
        this.type = type;
    }

    public Integer getMinGridDistance() {
        return minGridDistance;
    }

    public void setMinGridDistance(Integer minGridDistance) {
        this.minGridDistance = minGridDistance;
    }

    public Integer getRotation() {
        return rotation;
    }

    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }

    public String getCenterX() {
        return centerX;
    }

    public void setCenterX(String centerX) {
        this.centerX = centerX;
    }

    public String getCenterY() {
        return centerY;
    }

    public void setCenterY(String centerY) {
        this.centerY = centerY;
    }

    public Integer getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(Integer paddingRight) {
        this.paddingRight = paddingRight;
    }

    public String getCreateJS(String axisId) {
        boolean needsComma = false;
        String smallType = switch (type) {
            case X -> "x";
            case Y -> "y";
        };
        String bigType = smallType.toUpperCase(Locale.ROOT);

        StringBuilder code = new StringBuilder();
        code.append("var ").append(axisId).append("_").append(smallType).append("renderer = am5xy.AxisRenderer").append(bigType).append(".new(root, {");
        if (minGridDistance != null) {
            if (needsComma) {
                code.append(",");
            }
            code.append("minGridDistance: ").append(minGridDistance);
            needsComma = true;
        }
        code.append("});\n");
        needsComma = false;
        code.append(axisId).append("_").append(smallType).append("renderer.labels.template.setAll({\n");
        if (rotation != null) {
            if (needsComma) {
                code.append(",");
            }
            code.append("rotation: ").append(rotation);
            needsComma = true;
        }
        if (centerX != null) {
            if (needsComma) {
                code.append(",");
            }
            code.append("centerX: ").append(centerX);
            needsComma = true;
        }
        if (centerY != null) {
            if (needsComma) {
                code.append(",");
            }
            code.append("centerY: ").append(centerY);
            needsComma = true;
        }
        if (paddingRight != null) {
            if (needsComma) {
                code.append(",");
            }
            code.append("paddingRight: ").append(paddingRight);
            needsComma = true;
        }
        code.append("});\n");
        return code.toString();
    }
}
