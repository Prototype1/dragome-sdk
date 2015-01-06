package dragome.compiler.parser.visitors;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import dragome.compiler.model.method.MethodInfo;
import dragome.compiler.model.typeinfo.TypeInfo;
import dragome.compiler.model.typeinfo.ValueType;
import dragome.compiler.parser.model.BasicBlock;
import dragome.compiler.parser.model.ClassVars;
import dragome.compiler.parser.model.InvokeType;
import dragome.compiler.parser.model.OpCodeIns;
import dragome.compiler.parser.model.OpCodeParaInfo;
import dragome.compiler.parser.model.Scope;
import dragome.compiler.parser.model.Variable;

public class CustomMethodVisitor extends MethodVisitor
{

	private final Map<Integer, Variable> localVars= Maps.newHashMap();
	private final Set<MethodInfo> calledMethods= Sets.newHashSet();
	private final LinkedHashMap<Label, BasicBlock> instructions= new LinkedHashMap<>();
	private final ClassVars classVars;
	private final Map<String, ValueType> var= Maps.newHashMap();

	private int positionCounter= 0;
	private BasicBlock currBasicBlock;

	public CustomMethodVisitor(ClassVars classVars)
	{

		super(Opcodes.ASM5);

		this.classVars= classVars;

	}

	@Override
	public void visitJumpInsn(int opcode, Label label)
	{
		OpCodeIns ins= new OpCodeIns(opcode);
		ins.addParameter(OpCodeParaInfo.LABEL, label);

		currBasicBlock.addInstruction(ins);

	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String desc)
	{

		if (opcode == Opcodes.PUTFIELD || opcode == Opcodes.GETFIELD)
			currBasicBlock.setPositionFor(Scope.INSTANCE);
		else if (opcode == Opcodes.PUTSTATIC || opcode == Opcodes.GETSTATIC)
			currBasicBlock.setPositionFor(Scope.STATIC);

		OpCodeIns ins= new OpCodeIns(opcode);
		ins.addParameter(OpCodeParaInfo.OWNER, owner);
		ins.addParameter(OpCodeParaInfo.NAME, name);

		currBasicBlock.addInstruction(ins);

	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf)
	{

		InvokeType info= InvokeType.getFromOpCode(opcode);

		MethodInfo mInfo= new MethodInfo(desc, owner, name, info);

		OpCodeIns opCode= new OpCodeIns(opcode);
		opCode.addParameter(OpCodeParaInfo.INVOKE_TYPE, info);
		opCode.addParameter(OpCodeParaInfo.OWNER, owner);
		opCode.addParameter(OpCodeParaInfo.NAME, name);

		calledMethods.add(mInfo);
	}

	@Override
	public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index)
	{

		BasicBlock currBlock= instructions.get(start);
		Type t= Type.getType(desc);

		TypeInfo concretType= TypeInfo.createFromASMType(t);

		Variable var= new Variable(name, signature, Scope.LOCAL, concretType, index);

		localVars.put(index, var);

		currBlock.setPositionFor(Scope.LOCAL);
		currBlock.setTarget(var);

	}

	@Override
	public void visitVarInsn(int opcode, int var)
	{

		currBasicBlock.setPositionFor(Scope.LOCAL);

		if (opcode != Opcodes.RET)
			currBasicBlock.addInstruction(new OpCodeIns(opcode, var));

	}
	@Override
	public void visitLabel(Label label)
	{
		currBasicBlock= instructions.put(label, new BasicBlock(label, positionCounter));
		++positionCounter;
	}

	@Override
	public void visitLdcInsn(Object cst)
	{
		OpCodeIns ins= new OpCodeIns(Opcodes.LDC);
		ins.addParameter(OpCodeParaInfo.OBJECT_VALUE, cst);

		currBasicBlock.addInstruction(ins);
	}

	@Override
	public void visitTypeInsn(int opcode, String type)
	{
		OpCodeIns ins= new OpCodeIns(opcode);
		ins.addParameter(OpCodeParaInfo.NAME, type);

		currBasicBlock.addInstruction(ins);

	}

	@Override
	public void visitIntInsn(int opcode, int operand)
	{
		OpCodeIns ins= new OpCodeIns(opcode);

		if (opcode == Opcodes.NEWARRAY)
			ins.addParameter(OpCodeParaInfo.ARRAY_TYPE, operand);
		else
			ins.addParameter(OpCodeParaInfo.PRIMITIV_VALUE, operand);
	}

	@Override
	public void visitInsn(int opcode)
	{
		//not all instructions are important

	}

	@Override
	public void visitTryCatchBlock(Label start, Label end, Label handler, String type)
	{

		super.visitTryCatchBlock(start, end, handler, type);
	}

	@Override
	public void visitEnd()
	{
	}

}
