package com.toandoan.lol.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.TextView;

import com.toandoan.lol.R;

/**
 * Created by vuslenovo on 6/29/2015.
 */
public class RobotoTextView extends TextView{
    /*
     * Permissible values ??for the "typeface" attribute.
     */
    private final static int ROBOTO_LIGHT = 0;
    private final static int ROBOTO_REGULAR = 1;
    private final static int ROBOTO_BOLD = 2;
    private static final int ARISTA_LIGHT = 3;

    /**
     * List of created typefaces for later reused.
     */
    private final static SparseArray<Typeface> mTypefaces = new SparseArray<Typeface>();

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public RobotoTextView(Context context) {
        super(context);
    }

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     * <p/>
     * <p/>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     * @see #RobotoTextView(Context, AttributeSet, int)
     */
    public RobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    /**
     * Perform inflation from XML and apply a class-specific base style. This
     * constructor of View allows subclasses to use their own base style when
     * they are inflating.
     *
     * @param context  The Context the view is running in, through which it can
     *                 access the current theme, resources, etc.
     * @param attrs    The attributes of the XML tag that is inflating the view.
     * @param defStyle The default style to apply to this view. If 0, no style
     *                 will be applied (beyond what is included in the theme). This may
     *                 either be an attribute resource, whose value will be retrieved
     *                 from the current theme, or an explicit style resource.
     * @see #RobotoTextView(Context, AttributeSet)
     */
    public RobotoTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseAttributes(context, attrs);
    }

    /**
     * Parse the attributes.
     *
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     */
    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.RobotoTextView);

        int typefaceValue = values.getInt(R.styleable.RobotoTextView_typeface, 0);
        values.recycle();

        setTypeface(obtaintTypeface(context, typefaceValue));
    }

    /**
     * Obtain typeface.
     *
     * @param context       The Context the view is running in, through which it can
     *                      access the current theme, resources, etc.
     * @param typefaceValue values ??for the "typeface" attribute
     * @return Roboto {@link Typeface}
     * @throws IllegalArgumentException if unknown `typeface` attribute value.
     */
    private Typeface obtaintTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
        Typeface typeface = mTypefaces.get(typefaceValue);
        if (typeface == null) {
            typeface = createTypeface(context, typefaceValue);
            mTypefaces.put(typefaceValue, typeface);
        }
        return typeface;
    }

    /**
     * Create typeface from assets.
     *
     * @param context       The Context the view is running in, through which it can
     *                      access the current theme, resources, etc.
     * @param typefaceValue values ??for the "typeface" attribute
     * @return Roboto {@link Typeface}
     * @throws IllegalArgumentException if unknown `typeface` attribute value.
     */
    private Typeface createTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
        Typeface typeface;
        switch (typefaceValue) {
            case ROBOTO_LIGHT:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_light.ttf");
                break;
            case ROBOTO_REGULAR:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_regular.ttf");
                break;
            case ROBOTO_BOLD:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_bold.ttf");
                break;
            case ARISTA_LIGHT:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/arista_light.ttf");
                break;
            default:
                throw new IllegalArgumentException("Unknown 'typeface' attribute value " + typefaceValue);
        }
        return typeface;
    }

}