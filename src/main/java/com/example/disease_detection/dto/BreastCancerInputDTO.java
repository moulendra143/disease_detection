package com.example.disease_detection.dto;

public class BreastCancerInputDTO {

    private float radius_mean, texture_mean, perimeter_mean, area_mean, smoothness_mean;
    private float compactness_mean, concavity_mean, concave_points_mean, symmetry_mean, fractal_dimension_mean;
    private float radius_se, texture_se, perimeter_se, area_se, smoothness_se;
    private float compactness_se, concavity_se, concave_points_se, symmetry_se, fractal_dimension_se;
    private float radius_worst, texture_worst, perimeter_worst, area_worst, smoothness_worst;
    private float compactness_worst, concavity_worst, concave_points_worst, symmetry_worst, fractal_dimension_worst;

    // Getters & Setters (generate with IDE)
    public float getRadius_mean() { return radius_mean; }
    public void setRadius_mean(float radius_mean) { this.radius_mean = radius_mean; }
    public float getTexture_mean() { return texture_mean; }
    public void setTexture_mean(float texture_mean) { this.texture_mean = texture_mean; }
    public float getPerimeter_mean() { return perimeter_mean; }
    public void setPerimeter_mean(float perimeter_mean) { this.perimeter_mean = perimeter_mean; }
    public float getArea_mean() { return area_mean; }
    public void setArea_mean(float area_mean) { this.area_mean = area_mean; }
    public float getSmoothness_mean() { return smoothness_mean; }
    public void setSmoothness_mean(float smoothness_mean) { this.smoothness_mean = smoothness_mean; }
    public float getCompactness_mean() { return compactness_mean; }
    public void setCompactness_mean(float compactness_mean) { this.compactness_mean = compactness_mean; }
    public float getConcavity_mean() { return concavity_mean; }
    public void setConcavity_mean(float concavity_mean) { this.concavity_mean = concavity_mean; }
    public float getConcave_points_mean() { return concave_points_mean; }
    public void setConcave_points_mean(float concave_points_mean) { this.concave_points_mean = concave_points_mean; }
    public float getSymmetry_mean() { return symmetry_mean; }
    public void setSymmetry_mean(float symmetry_mean) { this.symmetry_mean = symmetry_mean; }
    public float getFractal_dimension_mean() { return fractal_dimension_mean; }
    public void setFractal_dimension_mean(float fractal_dimension_mean) { this.fractal_dimension_mean = fractal_dimension_mean; }

    // SE
    public float getRadius_se() { return radius_se; }
    public void setRadius_se(float radius_se) { this.radius_se = radius_se; }
    public float getTexture_se() { return texture_se; }
    public void setTexture_se(float texture_se) { this.texture_se = texture_se; }
    public float getPerimeter_se() { return perimeter_se; }
    public void setPerimeter_se(float perimeter_se) { this.perimeter_se = perimeter_se; }
    public float getArea_se() { return area_se; }
    public void setArea_se(float area_se) { this.area_se = area_se; }
    public float getSmoothness_se() { return smoothness_se; }
    public void setSmoothness_se(float smoothness_se) { this.smoothness_se = smoothness_se; }
    public float getCompactness_se() { return compactness_se; }
    public void setCompactness_se(float compactness_se) { this.compactness_se = compactness_se; }
    public float getConcavity_se() { return concavity_se; }
    public void setConcavity_se(float concavity_se) { this.concavity_se = concavity_se; }
    public float getConcave_points_se() { return concave_points_se; }
    public void setConcave_points_se(float concave_points_se) { this.concave_points_se = concave_points_se; }
    public float getSymmetry_se() { return symmetry_se; }
    public void setSymmetry_se(float symmetry_se) { this.symmetry_se = symmetry_se; }
    public float getFractal_dimension_se() { return fractal_dimension_se; }
    public void setFractal_dimension_se(float fractal_dimension_se) { this.fractal_dimension_se = fractal_dimension_se; }

    // Worst
    public float getRadius_worst() { return radius_worst; }
    public void setRadius_worst(float radius_worst) { this.radius_worst = radius_worst; }
    public float getTexture_worst() { return texture_worst; }
    public void setTexture_worst(float texture_worst) { this.texture_worst = texture_worst; }
    public float getPerimeter_worst() { return perimeter_worst; }
    public void setPerimeter_worst(float perimeter_worst) { this.perimeter_worst = perimeter_worst; }
    public float getArea_worst() { return area_worst; }
    public void setArea_worst(float area_worst) { this.area_worst = area_worst; }
    public float getSmoothness_worst() { return smoothness_worst; }
    public void setSmoothness_worst(float smoothness_worst) { this.smoothness_worst = smoothness_worst; }
    public float getCompactness_worst() { return compactness_worst; }
    public void setCompactness_worst(float compactness_worst) { this.compactness_worst = compactness_worst; }
    public float getConcavity_worst() { return concavity_worst; }
    public void setConcavity_worst(float concavity_worst) { this.concavity_worst = concavity_worst; }
    public float getConcave_points_worst() { return concave_points_worst; }
    public void setConcave_points_worst(float concave_points_worst) { this.concave_points_worst = concave_points_worst; }
    public float getSymmetry_worst() { return symmetry_worst; }
    public void setSymmetry_worst(float symmetry_worst) { this.symmetry_worst = symmetry_worst; }
    public float getFractal_dimension_worst() { return fractal_dimension_worst; }
    public void setFractal_dimension_worst(float fractal_dimension_worst) { this.fractal_dimension_worst = fractal_dimension_worst; }
}
