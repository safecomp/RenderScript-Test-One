#pragma version(1)
#pragma rs java_package_name(safecomp.ir.renderscripttestone)
#pragma rs_fp_relaxed


uchar4 __attribute__((kernel)) doinvert(uchar4 in)
{
    uchar4 out = in;
   	out.r = 255 - in.r;
   	out.g = 255 - in.g;
   	out.b = 255 - in.b;
   	return out;
}

