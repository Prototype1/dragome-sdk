package dragome.compiler.model.values;

import java.util.EnumMap;

import dragome.compiler.model.ValueType;
import dragome.compiler.model.typeinfo.AnnotationInfo;
import dragome.compiler.model.typeinfo.TypeInfo;

public class AnnotationValue extends ValueType
{

	private final EnumMap<AnnotationInfo, String> paramHolder= new EnumMap<>(AnnotationInfo.class);

	public AnnotationValue()
	{
		super(TypeInfo.ANNOTATION);

	}

	@Override
	public String toString()
	{
		
		return paramHolder.keySet().toString();
	}

}
