using System;
using System.CodeDom.Compiler;
using System.Reflection;
using System.Runtime.InteropServices;
using Microsoft.CSharp;


namespace CSharpExpressionEvaluator
{
    //Using attributes below to make the class exposed to COM
    [ClassInterface(ClassInterfaceType.AutoDual)]
    [ComVisible(true)]
    public class CSharpExpEval
    {
        static string Expression_error = "~Expression~Error~";
        static string Compile_error = "~Compile~Error~";
        static string Execute_error = "~Execute~Error~";

        /// <summary>
        /// Evaluate legal C# expression
        /// </summary>
        /// <param name="expession">expression</param>
        /// <returns>return result object</returns>
        public object Execute(string expession)
        {
            string classBody;
            if (expession.Contains("return") && expession.Contains(";"))
            {
                classBody =
                    "class Exp { delegate object Func(); public static object exec() { try { Func f = delegate() { " +
                    expession +
                    "}; return f.Invoke(); } catch { return \"" + Expression_error + "\"; } } }";
            }
            else
            {
                classBody = "class Exp { public static object exec() { try { return " +
                            expession +
                            ";} catch { return \"" + Expression_error + "\"; } } }";
            }

            //Shell of C# program
            string moduleSource =
                "using System;" +
                "using System.Text;" +
                "using System.Text.RegularExpressions;" +
                "namespace eval" +
                "{" +
                classBody +
                "}";

            try
            {
                using (CSharpCodeProvider codeProvider = new CSharpCodeProvider())
                {
                    CompilerParameters param = new CompilerParameters
                    {
                        GenerateExecutable = false,
                        GenerateInMemory = true
                    };

                    foreach (Assembly assembly in AppDomain.CurrentDomain.GetAssemblies())
                    {
                        try
                        {
                            param.ReferencedAssemblies.Add(assembly.Location);
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine(ex.Message);
                        }
                    }

                    CompilerResults compileResult = codeProvider.CompileAssemblyFromSource(param, moduleSource);

                    return GetExecutionResult(compileResult);
                }
            }
            catch (Exception)
            {
                return Compile_error;
            }

        }

        private object GetExecutionResult(CompilerResults compileResult)
        {
            object executionResult;
            if (compileResult.Errors.Count > 0)
            {
                executionResult = Execute_error;
            }
            else
            {
                try
                {
                    MethodInfo info = compileResult.CompiledAssembly.GetType("eval.Exp").GetMethod("exec");
                    executionResult = info.Invoke(null, null);
                }
                catch (Exception ex)
                {
                    executionResult = Execute_error;
                }
            }
            return executionResult;
        }
    }
}
